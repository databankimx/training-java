# verify-natives.ps1
#
# Checks the fetched JCEF natives against the approved hashes in natives.sha256.
# Each line of natives.sha256 is: <SHA-256 hash><two spaces><path relative to the natives folder>
# The script fails if a file is missing, its hash differs, or an unapproved file is present.
# It's run by the Maven build before the natives are bundled, so a changed file never reaches dist.

param(
    [Parameter(Mandatory = $true)]
    [string]$NativesDir
)

$ErrorActionPreference = "Stop"

# Maven passes a path with ".." in it, but the file paths below are full paths. Resolve the folder first, so the
# prefix removed from each file path has the same length.
$NativesDir = [System.IO.Path]::GetFullPath($NativesDir).TrimEnd('\', '/')

$manifest = Join-Path $NativesDir "natives.sha256"
if (-not (Test-Path $manifest)) {
    throw "No approved hash list at $manifest. Run the fetch-only mode first, then approve the hashes."
}

$approved = @{}
foreach ($line in Get-Content $manifest) {
    if ($line.Trim() -eq "") { continue }
    $parts = $line -split "  ", 2
    if ($parts.Count -ne 2) {
        throw "Malformed line in ${manifest}: $line"
    }
    $approved[$parts[1]] = $parts[0].ToLowerInvariant()
}

# Computes the SHA-256 of a file with .NET directly. Get-FileHash isn't available in every PowerShell host,
# including the one Maven starts, so the check doesn't depend on it.
function Get-Sha256Hex {
    param([string]$Path)
    $sha = [System.Security.Cryptography.SHA256]::Create()
    $stream = [System.IO.File]::OpenRead($Path)
    try {
        $bytes = $sha.ComputeHash($stream)
    }
    finally {
        $stream.Dispose()
        $sha.Dispose()
    }
    return ([System.BitConverter]::ToString($bytes) -replace '-', '').ToLowerInvariant()
}

$failures = @()
foreach ($relative in $approved.Keys) {
    $path = Join-Path $NativesDir $relative
    if (-not (Test-Path $path)) {
        $failures += "missing: $relative"
        continue
    }
    $actual = Get-Sha256Hex -Path $path
    if ($actual -ne $approved[$relative]) {
        $failures += "hash differs: $relative"
    }
}

$present = Get-ChildItem -Path $NativesDir -Recurse -File |
    Where-Object { $_.Name -ne "natives.sha256" } |
    ForEach-Object { $_.FullName.Substring($NativesDir.Length).TrimStart('\', '/') }
foreach ($relative in $present) {
    if (-not $approved.ContainsKey($relative)) {
        $failures += "not approved: $relative"
    }
}

if ($failures.Count -gt 0) {
    $failures | ForEach-Object { Write-Error $_ }
    throw "Natives do not match the approved hashes. $($failures.Count) problem(s) found."
}

Write-Host "Natives verified: $($approved.Count) file(s) match the approved hashes."
