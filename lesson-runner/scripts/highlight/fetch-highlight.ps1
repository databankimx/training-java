# fetch-highlight.ps1
#
# Downloads highlight.js 11.11.1 (BSD-3-Clause) into web\vendor\highlight, and records the SHA-256 hash of each file in
# web\vendor\highlight\highlight.sha256. Run it once, then commit the files and the hash list. The page loads the library
# from that folder, so the library never changes without the hashes showing it.
#
# Run from lesson-runner:
#   powershell -ExecutionPolicy Bypass -File .\scripts\highlight\fetch-highlight.ps1

$ErrorActionPreference = "Stop"

$version = "11.11.1"
$build = "https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@$version/build"
$source = "https://cdn.jsdelivr.net/gh/highlightjs/highlight.js@$version"
$target = Join-Path $PSScriptRoot "..\..\web\vendor\highlight"
New-Item -ItemType Directory -Force -Path (Join-Path $target "styles") | Out-Null

$files = @(
    @{ Url = "$build/highlight.min.js"; Path = "highlight.min.js" },
    @{ Url = "$build/styles/vs2015.min.css"; Path = "styles\vs2015.min.css" },
    @{ Url = "$build/styles/vs.min.css"; Path = "styles\vs.min.css" },
    @{ Url = "$source/LICENSE"; Path = "LICENSE.highlight.txt" }
)

$lines = @()
foreach ($file in $files) {
    $destination = Join-Path $target $file.Path
    Write-Host "Downloading $($file.Url)"
    Invoke-WebRequest -Uri $file.Url -OutFile $destination -UseBasicParsing
    $hash = (Get-FileHash -Algorithm SHA256 -Path $destination).Hash.ToLowerInvariant()
    $lines += "$hash  $($file.Path)"
}

Set-Content -Path (Join-Path $target "highlight.sha256") -Value $lines
Write-Host "Recorded $($lines.Count) file(s) in highlight.sha256. Commit them with the page."
