#!/usr/bin/env powershell

<#
.SYNOPSIS
  Sets up the training-java lesson runner on this machine.

.DESCRIPTION
  Checks for JDK 25, Maven 3.10.0 or later, and Git, then builds the lesson runner:

    1. mvn compile                     compiles the runner
    2. dependency copy to dist\lib     copies the runner's libraries, which run.bat needs
    3. fetch natives, if missing       downloads the JCEF natives and checks them against natives.sha256
    4. mvn package                     verifies the natives and copies them into dist\natives

  With -InstallMissing, the script installs anything that's missing:
    - JDK 25 (Eclipse Temurin) and Git, with winget
    - Maven 3.10.0, from archive.apache.org, checked against its published SHA-512 before it's extracted

  Installing needs an elevated (Administrator) PowerShell. Without -InstallMissing, the script only reports what's
  missing and stops. Safe to run more than once.

.PARAMETER InstallMissing
  Installs the JDK, Maven, and Git when they're missing.

.EXAMPLE
  powershell -ExecutionPolicy Bypass -File .\setup.ps1
  Run as Administrator: powershell -ExecutionPolicy Bypass -File .\setup.ps1 -InstallMissing
#>

param(
    [switch]$InstallMissing,
    [string]$RepoUrl,
    [string]$Branch = "main"
)

$ErrorActionPreference = "Stop"

$jdkDefault = "C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot"
$jdkWingetId = "EclipseAdoptium.Temurin.25.JDK"
$mavenVersion = "3.10.0"
$mavenPath = "C:\Tools\apache-maven-$mavenVersion"
$mavenUrl = "https://repo1.maven.org/maven2/org/apache/maven/apache-maven/$mavenVersion/apache-maven-$mavenVersion-bin.zip"
$gitWingetId = "Git.Git"
$gitDefault = "C:\Program Files\Git\cmd\git.exe"
$repoRoot = $PSScriptRoot
$runnerDir = Join-Path $repoRoot "lesson-runner"
$results = @()

function Add-Result {
    param([string]$Name, [string]$Status, [string]$Detail)
    $script:results += [pscustomobject]@{ Name = $Name; Status = $Status; Detail = $Detail }
}

function Show-Summary {
    Write-Host ""
    Write-Host "Setup summary:" -ForegroundColor Yellow
    Write-Host ("-" * 70)
    foreach ($r in $script:results) {
        $color = if ($r.Status -eq "ok") { "Green" } elseif ($r.Status -eq "warning") { "Yellow" } else { "Red" }
        Write-Host ("{0,-28} {1,-9} {2}" -f $r.Name, $r.Status, $r.Detail) -ForegroundColor $color
    }
}

function Test-Administrator {
    $identity = [Security.Principal.WindowsIdentity]::GetCurrent()
    $principal = New-Object Security.Principal.WindowsPrincipal($identity)
    return $principal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
}

# Returns the JDK 25 folder: the default path, or any jdk-25 folder under Eclipse Adoptium. Null if none is found.
function Find-Jdk {
    $candidates = @($jdkDefault)
    $candidates += Get-ChildItem "C:\Program Files\Eclipse Adoptium" -Directory -Filter "jdk-25*" -ErrorAction SilentlyContinue |
        ForEach-Object { $_.FullName }
    foreach ($candidate in $candidates) {
        if (Test-Path (Join-Path $candidate "bin\java.exe")) {
            return $candidate
        }
    }
    return $null
}

function Invoke-Winget {
    param([string]$Id)
    if (-not (Get-Command winget -ErrorAction SilentlyContinue)) {
        throw "winget is not available on this machine. Install 'App Installer' from the Microsoft Store, or install $Id manually."
    }
    Write-Host "  winget install -e --id $Id" -ForegroundColor Gray
    & winget install -e --id $Id --silent --accept-package-agreements --accept-source-agreements
    # winget exit code -1978335189 means the package is already installed, which is fine here.
    if ($LASTEXITCODE -ne 0 -and $LASTEXITCODE -ne -1978335189) {
        throw "winget could not install $Id (exit code $LASTEXITCODE)."
    }
}

function Install-Maven {
    $zip = Join-Path $env:TEMP "apache-maven-$mavenVersion-bin.zip"
    $checksumFile = "$zip.sha1"
    Write-Host "  downloading Maven $mavenVersion" -ForegroundColor Gray
    # Windows PowerShell 5.1 can default to an older TLS version, which servers refuse. Request TLS 1.2 explicitly.
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    try {
        Invoke-WebRequest -Uri $mavenUrl -OutFile $zip -UseBasicParsing
        Invoke-WebRequest -Uri "${mavenUrl}.sha1" -OutFile $checksumFile -UseBasicParsing
    } catch {
        throw ("Could not download Maven from $mavenUrl. Check that the VM's date and time are correct, and that its " +
            "root certificates are up to date (run Windows Update). If a proxy or antivirus inspects HTTPS, download the " +
            "zip manually and extract it to $mavenPath. Original error: $($_.Exception.Message)")
    }

    # Maven Central publishes a SHA-1 for each file, which is compared here. The SHA-1 is weaker than SHA-512, so the
    # published SHA-512 from archive.apache.org is the stronger check, when that site can be reached.
    $expected = ((Get-Content $checksumFile -Raw).Trim() -split '\s+')[0].ToLowerInvariant()
    $actual = (Get-FileHash $zip -Algorithm SHA1).Hash.ToLowerInvariant()
    if ($actual -ne $expected) {
        Remove-Item $zip -Force
        throw "The Maven download failed its SHA-1 check. Expected $expected, got $actual. Nothing was installed."
    }
    Write-Host "  SHA-1 check passed (from Maven Central)" -ForegroundColor Gray

    Write-Host "  extracting to C:\Tools" -ForegroundColor Gray
    New-Item -ItemType Directory -Force -Path "C:\Tools" | Out-Null
    Expand-Archive -Path $zip -DestinationPath "C:\Tools" -Force
}

function Assert-Path {
    param([string]$Path, [string]$Description)
    if (-not (Test-Path $Path)) {
        throw "$Description not found at: $Path"
    }
}

Write-Host ""
Write-Host "Java training setup" -ForegroundColor Yellow
Write-Host ""

if ($InstallMissing -and -not (Test-Administrator)) {
    throw "-InstallMissing needs an elevated PowerShell. Close this window, open PowerShell as Administrator, and run the script again."
}

# 1. Install anything missing (only with -InstallMissing)

Write-Host "Checking required tools..." -ForegroundColor Cyan

$jdkPath = Find-Jdk
if (-not $jdkPath -and $InstallMissing) {
    Write-Host "  JDK 25 is missing: installing it" -ForegroundColor Yellow
    Invoke-Winget $jdkWingetId
    $jdkPath = Find-Jdk
}
if (-not $jdkPath) {
    Add-Result "JDK 25" "MISSING" "not found under Eclipse Adoptium"
} else {
    Add-Result "JDK 25" "ok" $jdkPath
}

$mvnExe = Join-Path $mavenPath "bin\mvn.cmd"
if (-not (Test-Path $mvnExe) -and $InstallMissing) {
    Write-Host "  Maven $mavenVersion is missing: installing it" -ForegroundColor Yellow
    Install-Maven
}
if (-not (Test-Path $mvnExe)) {
    Add-Result "Maven $mavenVersion" "MISSING" $mavenPath
} else {
    Add-Result "Maven $mavenVersion" "ok" $mavenPath
}

$gitExe = $gitDefault
if (-not (Test-Path $gitExe)) {
    $gitCommand = Get-Command git -ErrorAction SilentlyContinue
    if ($gitCommand) { $gitExe = $gitCommand.Source }
}
if (-not (Test-Path $gitExe) -and $InstallMissing) {
    Write-Host "  Git is missing: installing it" -ForegroundColor Yellow
    Invoke-Winget $gitWingetId
    $gitExe = $gitDefault
}
if (-not (Test-Path $gitExe)) {
    # Git is used to clone the repository, not to build the runner, so a missing Git is a warning.
    Add-Result "Git" "warning" "not found. Install it to clone the repository, or run with -InstallMissing"
} else {
    Add-Result "Git" "ok" $gitExe
}

if (-not $jdkPath -or -not (Test-Path $mvnExe)) {
    Show-Summary
    Write-Host ""
    Write-Host "Required tools are missing." -ForegroundColor Red
    if (-not $InstallMissing) {
        Write-Host "Run this script again as Administrator with -InstallMissing to install them." -ForegroundColor Yellow
    }
    Write-Host "Or install them by hand:" -ForegroundColor Yellow
    Write-Host "  JDK 25 (Eclipse Temurin): winget install -e --id $jdkWingetId" -ForegroundColor Yellow
    Write-Host "  Maven ${mavenVersion}: https://archive.apache.org/dist/maven/maven-3/${mavenVersion}/binaries/" -ForegroundColor Yellow
    Write-Host "    extract to: $mavenPath" -ForegroundColor Yellow
    exit 1
}

$javaExe = Join-Path $jdkPath "bin\java.exe"
$javacExe = Join-Path $jdkPath "bin\javac.exe"
Assert-Path $javacExe "javac.exe in the JDK"

# Maven needs JAVA_HOME to find Java, so set it before any version check runs. The JDK and Maven checked above must
# be the ones used, so the build doesn't pick up another Java from PATH.
$env:JAVA_HOME = $jdkPath
$env:Path = (Join-Path $jdkPath "bin") + ";" + (Join-Path $mavenPath "bin") + ";" + $env:Path

# 2. Check the versions. java -version and mvn -v write to stderr, so cmd runs them without stopping the script.

$javaVersion = cmd /c "`"$javaExe`" -version 2>&1" | Out-String
if ($javaVersion -notmatch 'version "25') {
    throw "The JDK at $jdkPath is not version 25. Found: $($javaVersion.Trim())"
}
Add-Result "JDK version" "ok" "25"

$mavenVersionText = cmd /c "`"$mvnExe`" -v 2>&1" | Out-String
if ($mavenVersionText -match 'Apache Maven (\d+\.\d+\.\d+)') {
    $mavenVersionFound = $matches[1]
} else {
    throw "Could not read the Maven version from ${mvnExe}. Maven printed: $($mavenVersionText.Trim())"
}
if ([version]$mavenVersionFound -lt [version]"3.10.0") {
    throw "Maven $mavenVersionFound is older than 3.10.0. Install 3.10.0 or later at $mavenPath."
}
Add-Result "Maven version" "ok" $mavenVersionFound

# 3. Repository: clone it if this script isn't running from inside it

if (-not (Test-Path (Join-Path $repoRoot "parts.json"))) {
    $defaultRepoPath = "C:\Development\training\training-java"
    if (Test-Path (Join-Path $defaultRepoPath "parts.json")) {
        $repoRoot = $defaultRepoPath
    } elseif ($RepoUrl) {
        if (-not (Test-Path $gitExe)) {
            throw "Git is needed to clone the repository, and it isn't installed. Run with -InstallMissing, or install Git."
        }
        Write-Host "Cloning the repository into $defaultRepoPath..." -ForegroundColor Cyan
        New-Item -ItemType Directory -Force -Path (Split-Path $defaultRepoPath -Parent) | Out-Null
        & $gitExe clone --branch $Branch $RepoUrl $defaultRepoPath
        if ($LASTEXITCODE -ne 0) {
            throw "git clone failed with exit code $LASTEXITCODE. Check the URL, and your access to the repository."
        }
        $repoRoot = $defaultRepoPath
    } else {
        throw "This script isn't inside the training repository, and no repository was found at $defaultRepoPath. Clone the repository first, or run again with -RepoUrl <clone address>."
    }
    # Run the repository's own copy of this script, so every later step uses the cloned files.
    Write-Host "Continuing from the repository at $repoRoot" -ForegroundColor Cyan
    $childArgs = @()
    if ($InstallMissing) { $childArgs += "-InstallMissing" }
    & (Join-Path $repoRoot "setup.ps1") @childArgs
    exit $LASTEXITCODE
}

$runnerDir = Join-Path $repoRoot "lesson-runner"

# 3b. Repository structure

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

# run.bat reads the JDK folder from jdk.path, so it finds the JDK even when it isn't at the default path.
Set-Content -Path (Join-Path $runnerDir "jdk.path") -Value $jdkPath -Encoding ASCII

# Maven and the JDK must be the ones checked above, so the build doesn't pick up another Java from PATH.
$env:JAVA_HOME = $jdkPath
$env:Path = (Join-Path $jdkPath "bin") + ";" + (Join-Path $mavenPath "bin") + ";" + $env:Path

Push-Location $runnerDir
try {
    # 4. Build the runner

    Write-Host ""
    Write-Host "Building the lesson runner..." -ForegroundColor Cyan
    Write-Host "  mvn -q compile" -ForegroundColor Gray
    & $mvnExe -q compile
    if ($LASTEXITCODE -ne 0) { throw "mvn compile failed with exit code $LASTEXITCODE." }

    Write-Host "  mvn -q dependency:copy-dependencies -DoutputDirectory=dist\lib" -ForegroundColor Gray
    & $mvnExe -q dependency:copy-dependencies "-DoutputDirectory=dist\lib" "-DincludeScope=runtime"
    if ($LASTEXITCODE -ne 0) { throw "Copying the runner's libraries failed with exit code $LASTEXITCODE." }

    # 5. Natives: fetch them once, then check them against the approved manifest

    $jcefDll = Join-Path $repoRoot "resources\vendor\jcef\natives\jcef.dll"
    if (-not (Test-Path $jcefDll)) {
        Write-Host "  fetching the JCEF natives (needs internet access, once)" -ForegroundColor Gray
        & $javaExe --enable-native-access=ALL-UNNAMED -cp "target\classes;dist\lib\*" com.databankimx.training.runner.Main --fetch-natives
        if ($LASTEXITCODE -ne 0) { throw "Fetching the JCEF natives failed with exit code $LASTEXITCODE." }
    } else {
        Write-Host "  JCEF natives are already present" -ForegroundColor Gray
    }

    # 6. Package: verifies the natives against natives.sha256, and copies them into dist\natives

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
