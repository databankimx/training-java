#!/usr/bin/env powershell

<#
.SYNOPSIS
  Sets up the training-java lesson runner.

.DESCRIPTION
  Checks for required tools (JDK 25, Maven), and builds the runner.
  Safe to run multiple times.

.PARAMETER InstallMissing
  If specified, attempts to download and install missing dependencies.
  Without it, the script reports what's missing and stops.

.EXAMPLE
  .\setup.ps1
  .\setup.ps1 -InstallMissing
#>

param(
    [switch]$InstallMissing
)

$ErrorActionPreference = "Stop"
$WarningPreference = "Continue"

$results = @()

function Test-Tool {
    param(
        [string]$Name,
        [string]$ExpectedPath
    )
    
    Write-Host "Checking $Name..." -ForegroundColor Cyan
    
    if ($ExpectedPath -and (Test-Path $ExpectedPath)) {
        Write-Host "  ✓ Found at $ExpectedPath" -ForegroundColor Green
        $script:results += @{Name=$Name; Status="ok"; Path=$ExpectedPath}
        return $true
    }
    
    Write-Host "  ✗ Not found at $ExpectedPath" -ForegroundColor Red
    $script:results += @{Name=$Name; Status="missing"; Path=$ExpectedPath}
    return $false
}

function Assert-Directory {
    param(
        [string]$Path,
        [string]$Description
    )
    
    if (-not (Test-Path $Path)) {
        throw "$Description not found at: $Path"
    }
}

# ============================================================================
# Check for required tools
# ============================================================================

Write-Host "`nJava Training Setup`n" -ForegroundColor Yellow

$jdkPath = "C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot"
$javaExe = Join-Path $jdkPath "bin\java.exe"
$jdkFound = Test-Tool -Name "JDK 25 (Eclipse Temurin)" -ExpectedPath $javaExe

$mavenPath = "C:\Tools\apache-maven-3.10.0"
$mvnExe = Join-Path $mavenPath "bin\mvn.cmd"
$mavenFound = Test-Tool -Name "Maven 3.10.0" -ExpectedPath $mvnExe

# ============================================================================
# Check repository structure
# ============================================================================

Write-Host "`nChecking repository structure..." -ForegroundColor Cyan
$repoRoot = Split-Path -Parent $PSScriptRoot

Assert-Directory (Join-Path $repoRoot "lesson-runner") "lesson-runner folder"
Assert-Directory (Join-Path $repoRoot "resources\vendor\jcef\natives") "JCEF natives folder"
Assert-Directory (Join-Path $repoRoot "lesson-runner\web") "lesson-runner web folder"
Assert-Directory (Join-Path $repoRoot "lesson-runner\scripts") "lesson-runner scripts folder"

Write-Host "  ✓ Repository structure is correct" -ForegroundColor Green
$results += @{Name="Repository structure"; Status="ok"; Path=$repoRoot}

# ============================================================================
# Report results and exit if tools are missing
# ============================================================================

if (-not $jdkFound -or -not $mavenFound) {
    Write-Host "`n" -ForegroundColor Yellow
    Write-Host "Setup Summary:" -ForegroundColor Yellow
    Write-Host "-" * 60
    
    foreach ($r in $results) {
        $status = if ($r.Status -eq "ok") { "✓ ok" } else { "✗ MISSING" }
        Write-Host ("{0,-40} {1,-20}" -f $r.Name, $status) -ForegroundColor $(if ($r.Status -eq "ok") { "Green" } else { "Red" })
    }
    
    Write-Host "`nRequired tools are missing. Install them manually:" -ForegroundColor Red
    if (-not $jdkFound) {
        Write-Host "  • JDK 25 (Eclipse Temurin): https://adoptium.net/temurin/releases/" -ForegroundColor Yellow
        Write-Host "    Install to: $jdkPath" -ForegroundColor Yellow
    }
    if (-not $mavenFound) {
        Write-Host "  • Apache Maven 3.10.0: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
        Write-Host "    Install to: $mavenPath" -ForegroundColor Yellow
    }
    
    exit 1
}

# ============================================================================
# Build the runner
# ============================================================================

Write-Host "`nBuilding lesson runner..." -ForegroundColor Cyan

$lessonRunnerDir = Join-Path $repoRoot "lesson-runner"
Push-Location $lessonRunnerDir

try {
    # Run Maven compile to build classes
    Write-Host "  Running: mvn -q compile" -ForegroundColor Gray
    & mvn -q compile
    
    if ($LASTEXITCODE -ne 0) {
        throw "Maven build failed with exit code $LASTEXITCODE"
    }
    
    Write-Host "  ✓ Build succeeded" -ForegroundColor Green
    $results += @{Name="Maven build (mvn compile)"; Status="ok"; Path="target\classes"}
} finally {
    Pop-Location
}

# ============================================================================
# Print summary
# ============================================================================

Write-Host "`n" -ForegroundColor Yellow
Write-Host "Setup Summary:" -ForegroundColor Yellow
Write-Host "-" * 60

foreach ($r in $results) {
    $status = if ($r.Status -eq "ok") { "✓ ok" } else { "✗ MISSING" }
    Write-Host ("{0,-40} {1,-20}" -f $r.Name, $status) -ForegroundColor $(if ($r.Status -eq "ok") { "Green" } else { "Red" })
}

Write-Host "`n✓ Setup complete! The lesson runner is ready." -ForegroundColor Green
Write-Host "`nNext step: Run the lesson runner" -ForegroundColor Cyan
Write-Host "  cd lesson-runner" -ForegroundColor Gray
Write-Host "  .\run.bat" -ForegroundColor Gray
Write-Host ""
