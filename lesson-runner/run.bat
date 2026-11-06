@echo off
rem Starts the lesson runner. Run mvn -q compile first, so target\classes is up to date.
rem setup.ps1 writes the JDK folder to jdk.path. Without that file, the default Temurin 25 path is used.
rem The --enable-native-access flag stops JCEF's native loading from printing a warning on JDK 25.
rem The --add-exports flags are needed for off-screen rendering (JOGL) on JDK 16 and later.
cd /d "%~dp0"
set "JDK_DIR=C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot"
if exist "jdk.path" set /p JDK_DIR=<"jdk.path"
set "JAVA=%JDK_DIR%\bin\java.exe"
if not exist "%JAVA%" (
  echo java.exe not found at "%JAVA%". Run setup.ps1, or check the JDK path in jdk.path.
  exit /b 1
)
"%JAVA%" --enable-native-access=ALL-UNNAMED --add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED -cp "target\classes;dist\lib\*" com.databankimx.training.runner.Main %*
