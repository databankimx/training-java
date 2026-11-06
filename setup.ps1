#!/usr/bin/env powershell

<#
.SYNOPSIS
  Sets up the training-java lesson runner on this machine.

.DESCRIPTION
  Checks for JDK 25 (java.exe and javac.exe) and Maven 3.10.0 or later at the paths the course uses, checks the
  repository structure, and builds the lesson runner:

    1. mvn compile                     compiles the runner
    2. dependency copy to dist\lib     copies the runner's libraries, which run.bat needs
    3. fetch natives, if missing       downloads the JCEF natives and checks them against natives.sha256
    4. mvn package                     verifies the natives and copies them into dist\natives

  Safe to run more than once. An existing, approved natives.sha256 is never overwritten.

.EXAMPLE
  powershell -ExecutionPolicy Bypass -File .\setup.ps1
#>

$ErrorActionPreference = "Stop"

$jdkPath = "C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot"
$javaExe = Join-Path $jdkPath "bin\java.exe"
$javacExe = Join-Path $jdkPath "bin\javac.exe"
$mavenPath = "C:\Tools\apache-maven-3.10.0"
$mvnExe = Join-Path $mavenPath "bin\mvn.cmd"
$repoRoot = $PSScriptRoot
$runnerDir = Join-Path $repoRoot "lesson-runner"
$results = @()

function Add-Result {
    param([string]$Name, [string]$Status, [string]$Detail)
    $script:results += [pscustomobject]@{ Name = $Name; Status = $Status; Detail = $Detail }
}

function Test-Tool {
    param([string]$Name, [string]$Path)
    Write-Host "Checking $Name..." -ForegroundColor Cyan
    if ($Path -and (Test-Path $Path)) {
        Write-Host "  found at $Path" -ForegroundColor Green
        Add-Result $Name "ok" $Path
        return $true
    }
    Write-Host "  not found at $Path" -ForegroundColor Red
    Add-Result $Name "MISSING" $Path
    return $false
}

function Assert-Path {
    param([string]$Path, [string]$Description)
    if (-not (Test-Path $Path)) {
        throw "$Description not found at: $Path"
    }
}

function Show-Summary {
    Write-Host ""
    Write-Host "Setup summary:" -ForegroundColor Yellow
    Write-Host ("-" * 70)
    foreach ($r in $script:results) {
        $color = if ($r.Status -eq "ok") { "Green" } else { "Red" }
        Write-Host ("{0,-38} {1,-9} {2}" -f $r.Name, $r.Status, $r.Detail) -ForegroundColor $color
    }
}

Write-Host ""
Write-Host "Java training setup" -ForegroundColor Yellow
Write-Host ""

# 1. Required tools

$jdkFound = Test-Tool -Name "JDK 25 (java.exe)" -Path $javaExe
$javacFound = Test-Tool -Name "JDK 25 (javac.exe)" -Path $javacExe
$mavenFound = Test-Tool -Name "Maven 3.10.0 or later" -Path $mvnExe

if (-not ($jdkFound -and $javacFound -and $mavenFound)) {
    Show-Summary
    Write-Host ""
    Write-Host "Required tools are missing. Install them, then run this script again:" -ForegroundColor Red
    Write-Host "  JDK 25 (Eclipse Temurin): winget install -e --id EclipseAdoptium.Temurin.25.JDK" -ForegroundColor Yellow
    Write-Host "    expected at: $jdkPath" -ForegroundColor Yellow
    Write-Host "  Maven 3.10.0: download from https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    Write-Host "    expected at: $mavenPath" -ForegroundColor Yellow
    exit 1
}

# Check the versions. java -version and mvn -v write to stderr, so cmd runs them without stopping the script.
$javaVersion = cmd /c "`"$javaExe`" -version 2>&1" | Out-String
if ($javaVersion -notmatch 'version "25') {
    throw "The JDK at $jdkPath is not version 25. Found: $($javaVersion.Trim())"
}
Add-Result "JDK version" "ok" "25"

$mavenVersion = cmd /c "`"$mvnExe`" -v 2>&1" | Out-String
if ($mavenVersion -match 'Apache Maven (\d+\.\d+\.\d+)') {
    $mavenVersionFound = $matches[1]
} else {
    throw "Could not read the Maven version from $mvnExe"
}
if ([version]$mavenVersionFound -lt [version]"3.10.0") {
    throw "Maven $mavenVersionFound is older than 3.10.0. Install 3.10.0 or later at $mavenPath."
}
Add-Result "Maven version" "ok" $mavenVersionFound

# 2. Repository structure

Write-Host ""
Write-Host "Checking the repository structure..." -ForegroundColor Cyan
Assert-Path (Join-Path $repoRoot "parts.json") "parts.json"
Assert-Path (Join-Path $runnerDir "pom.xml") "lesson runner pom.xml"
Assert-Path (Join-Path $runnerDir "web") "lesson runner web folder"
Assert-Path (Join-Path $runnerDir "run.bat") "lesson runner run.bat"
Assert-Path (Join-Path $runnerDir "scripts\verify-natives.ps1") "native verification script"
Assert-Path (Join-Path $repoRoot "resources\vendor\junit\junit-platform-console-standalone.jar") "JUnit console launcher"
Assert-Path (Join-Path $repoRoot "resources\vendor\jcef\natives\natives.sha256") "approved natives manifest"
Add-Result "Repository structure" "ok" $repoRoot

# Maven and the JDK must be the ones checked above, so the build doesn't pick up another Java from PATH.
$env:JAVA_HOME = $jdkPath
$env:Path = (Join-Path $jdkPath "bin") + ";" + (Join-Path $mavenPath "bin") + ";" + $env:Path

Push-Location $runnerDir
try {
    # 3. Build the runner

    Write-Host ""
    Write-Host "Building the lesson runner..." -ForegroundColor Cyan
    Write-Host "  mvn -q compile" -ForegroundColor Gray
    & $mvnExe -q compile
    if ($LASTEXITCODE -ne 0) { throw "mvn compile failed with exit code $LASTEXITCODE." }

    Write-Host "  mvn -q dependency:copy-dependencies -DoutputDirectory=dist\lib" -ForegroundColor Gray
    & $mvnExe -q dependency:copy-dependencies "-DoutputDirectory=dist\lib" "-DincludeScope=runtime"
    if ($LASTEXITCODE -ne 0) { throw "Copying the runner's libraries failed with exit code $LASTEXITCODE." }

    # 4. Natives: fetch them once, then check them against the approved manifest

    $jcefDll = Join-Path $repoRoot "resources\vendor\jcef\natives\jcef.dll"
    if (-not (Test-Path $jcefDll)) {
        Write-Host "  fetching the JCEF natives (needs internet access, once)" -ForegroundColor Gray
        & $javaExe -cp "target\classes;dist\lib\*" com.databankimx.training.runner.Main --fetch-natives
        if ($LASTEXITCODE -ne 0) { throw "Fetching the JCEF natives failed with exit code $LASTEXITCODE." }
    } else {
        Write-Host "  JCEF natives are already present" -ForegroundColor Gray
    }

    # 5. Package: verifies the natives against natives.sha256, and copies them into dist\natives

    Write-Host "  mvn -q package" -ForegroundColor Gray
    & $mvnExe -q package
    if ($LASTEXITCODE -ne 0) { throw "mvn package failed with exit code $LASTEXITCODE. See the messages above." }

    Assert-Path (Join-Path $runnerDir "dist\lib") "runner libraries (dist\lib)"
    Assert-Path (Join-Path $runnerDir "dist\natives\jcef.dll") "bundled natives (dist\natives)"
    Add-Result "Lesson runner build" "ok" "dist\lib and dist\natives"
} finally {
    Pop-Location
}

Show-Summary

Write-Host ""
Write-Host "Setup complete. Start the lesson runner with:" -ForegroundColor Green
Write-Host "  cd lesson-runner" -ForegroundColor Gray
Write-Host "  .\run.bat" -ForegroundColor Gray
Write-Host ""
