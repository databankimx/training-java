@echo off
rem Starts the lesson runner. Run mvn -q compile first, so target\classes is up to date.
rem The runner is pinned to the Temurin 25 runtime, so it matches the classes compiled by Maven.
rem The --add-exports flags are needed for off-screen rendering (JOGL) on JDK 16 and later.
cd /d "%~dp0"
set "JAVA=C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot\bin\java.exe"
"%JAVA%" --add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED -cp "target\classes;dist\lib\*" com.databankimx.training.runner.Main %*
