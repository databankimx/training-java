# Quickstart

This guide takes you from a fresh machine to a working lesson runner, where every lesson example can run. Follow the steps in order. Each step says how to check it worked. The last section covers the errors you're most likely to see.

Commands are for **Windows PowerShell**, and assume you're in the repository root, `training-java`, unless a step says otherwise.

---

## Where to clone the repository

Clone the repository under `C:\Development`, for example `C:\Development\training\training-java`. The lesson runner builds and runs each exercise inside the repository, in a folder called `.lesson-runner`. Security software on managed machines is set up to allow that folder when it's under `C:\Development`. A copy somewhere else, such as your Downloads folder or a temporary folder, may have its new programs removed as they're built.

---

## Quick setup with the script

### What gets installed

By default, the setup installs:

* Git
* PowerShell 7
* Java JDK 25
* Apache Maven 3.10.0
* Microsoft Visual C++ runtime
* VS Code, with the extensions redhat.java, vscjava.vscode-maven, vscjava.vscode-java-pack
* Notepad++

The setup then runs these steps: Record the JDK folder for the runner; Compile the runner; Clear the runner's old libraries; Copy the runner's libraries; Fetch the JCEF natives; Verify the natives; Package the runner. At the end it opens the repository in VS Code and starts the lesson runner program.

### Getting started

There are three methods available to set up this tutorial. They are currently Windows-only.

#### Full Automation (if you don't have Git currently set up)

This process will install Git and copy the repository to a subfolder of `C:\Development\training` and install all necessary software and extensions for the tutorial's lesson runner.

1. Obtain the tutorial's GitHub slug name, which is `training-java`
2. Open PowerShell using "Run as Administrator"
3. Run the script below

```pwsh
# Obtain the load tutorial script
irm https://raw.githubusercontent.com/databankimx/training-shared-template/main/load-tutorial.ps1 -OutFile "$env:TEMP\load-tutorial.ps1"

# Perform automated setup
powershell -NoProfile -ExecutionPolicy Bypass -File "$env:TEMP\load-tutorial.ps1" -RepoUrl https://github.com/databankimx/training-java.git
```

#### Automated Setup from Local Repo (assumes you already have Git installed and set up)

In this process, you pull the existing repository and then run its setup script, which installs all necessary software and extensions for the tutorial's lesson runner.

1. Obtain the tutorial's GitHub slug name, which is `training-java`
2. Open PowerShell using "Run as Administrator"
3. If you will use the default base directory `C:\Development\training`, run the following:

```pwsh
# Create the base folder if it doesn't exist yet
New-Item -ItemType Directory -Force -Path C:\Development\training | Out-Null
```

4. Clone the repository. If you're using a different base directory, replace `C:\Development\training` with your path:

```pwsh
# Go there and clone the tutorial
cd C:\Development\training
git clone https://github.com/databankimx/training-java.git
cd training-java
```

5. If desired, edit the setup configuration, which determines what software is installed automatically (see "Change what gets installed" below)

```pwsh
# Optional: edit setup.json before installations
notepad setup.json
```

6. Save the configuration if you edited it, then run the setup script

##### If using the default base path, run this:

```pwsh
# Run the setup
powershell -NoProfile -ExecutionPolicy Bypass -File .\setup.ps1
```

##### If using a different base path, replace `<BASE_DIR>` with your path and run this:

```pwsh
# Run the setup
powershell -NoProfile -ExecutionPolicy Bypass -File .\setup.ps1 -BaseDirectory <BASE_DIR>
```

#### Manual Setup from Local Repo

If you want to do everything manually, you can pull the repository and set up software on your own.

1. Obtain the tutorial's GitHub slug name, which is `training-java`
2. Run the script below

```pwsh
git clone https://github.com/databankimx/training-java.git
```

3. Review README.md and QUICKSTART.md in the repository for instructions for manual setup.

### Switches and notes

* `-SkipInstall` checks what's installed and installs nothing.
* `-NoLaunch` runs the setup but doesn't open VS Code or start the runner.
* Installing uses winget. If winget isn't available, install **App Installer** from the Microsoft Store first. Company policy can block installs, in which case the script stops and you'll need IT.
* If an install needs administrator rights, the script stops before installing anything and asks you to run it again from an Administrator PowerShell window.

### Change what gets installed

The settings are in `setup.json`, in the repository folder. Open it in any text editor:

* Set an install's `"install"` to `true` to include it, or `false` to skip it.
* `"pinned"` sets the version. `"extensions"` lists the VS Code extensions to add.
* Save the file, then run `setup.ps1` again. It skips what's already installed.

## 1. Install the JDK

The script does this step by default. To install it yourself, use winget in PowerShell:

```pwsh
winget install -e --id EclipseAdoptium.Temurin.25.JDK
```

Or download the JDK 25 installer from [adoptium.net](https://adoptium.net/temurin/releases/?version=25) and install it.

**Check:** `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot\bin\java.exe` exists, or another `jdk-25` folder exists under `C:\Program Files\Eclipse Adoptium`. The setup script finds either one.

---

## 2. Install Maven

The script does this step by default. To install it by hand, download **Maven 3.10.0** from [maven.apache.org](https://maven.apache.org/download.cgi) (the binary zip), and extract it so that the folder `C:\Tools\apache-maven-3.10.0` exists. The setup script looks for it at that path.

Don't use winget for Maven. Its install folder isn't the one the script looks in.

**Check:** `C:\Tools\apache-maven-3.10.0\bin\mvn.cmd` exists.

---

## 3. Run the setup script

From the repository root:

```pwsh
powershell -ExecutionPolicy Bypass -File .\setup.ps1
```

The script installs missing tools by default. To check only, without installing anything, run it with `-InstallMissing:$false`.

The script checks the JDK and Maven versions, verifies the repository, and builds the runner. It copies the runner's libraries into `lesson-runner\dist\lib`. It downloads the browser's native files (JCEF) once, checks them against the approved list in `resources\vendor\jcef\natives\natives.sha256`, and copies them into `lesson-runner\dist\natives`.

The first run needs internet access, to download the browser files. Later runs reuse them.

**Check:** the summary at the end shows `ok` for each line, and the last line says the runner is built.

---

## 4. Start the lesson runner

From the repository root:

```pwsh
cd lesson-runner
.\run.bat
```

A window titled **Lesson Runner** opens, with the chapters on the left. Click a chapter to see its steps, then click a step to open it.

**Check:** the window shows the chapter list, and the Ch01 chapter opens when you click it.

---

## 5. Your first run of each kind of step

Work through these once, to confirm each kind of example runs.

| Kind | Where | What you should see |
|---|---|---|
| Program with output | Ch01, 01-hello-world | `Hello, World!` in the Output pane. Press **Run** (or F5) |
| Source you can edit | Ch01, 01-hello-world | The **Source** tab shows `Main.java`. Edit it and press **Run** to see the change. **Reset** restores the original |
| Program that waits for input | Ch02, user input | A prompt. Type a line in the input box, and press Enter |
| Stopping a run | Ch02, user input | Press Run, then **Stop** or Escape before typing. The Run button comes back |
| Test button | A step with tests | Press **Test**. The output reports the tests that ran |

The first run of each step builds it, so it can take several seconds.

---

## 6. If you're using VS Code

The lesson runner has its own editor, so VS Code is optional. If you want it for editing the code, install VS Code from [code.visualstudio.com](https://code.visualstudio.com), and open the repository root.

Add the **Extension Pack for Java** (publisher Microsoft) yourself if the setup script couldn't install it. It checks your code as you type, and offers completion and navigation. It uses the JDK that the setup script found, so restart VS Code after setup if the JDK doesn't appear in the status bar.

**Check:** the Extensions view lists the Java extension pack as installed, and the status bar shows the JDK 25 version.

---

## Troubleshooting

| What you see | What to do |
|---|---|
| The setup script says the JDK is missing | Run it again from an Administrator PowerShell, or install the JDK (step 1). |
| The setup script says Maven is missing | Run it again from an Administrator PowerShell, or install Maven to `C:\Tools\apache-maven-3.10.0` (step 2). |
| The script says it can't resolve or download Maven | Check the network. The download is from Maven Central, so the machine needs access to `repo1.maven.org`. |
| The setup script says a native file doesn't match the approved hashes | The downloaded browser files differ from the approved list in `natives.sha256`. Don't change the list. Tell the course owner, who decides whether the change is approved. |
| The setup script fails while downloading the natives | Check your internet access, and that a proxy or security software isn't blocking the download. Then run the script again. |
| `run.bat` can't find `java.exe` | Run `setup.ps1` again, so it records the JDK folder in `lesson-runner\jdk.path`. |
| Maven build fails | Run `mvn -q compile` in `lesson-runner` to see the full error. |
| Console shows `Exception in thread "AWT-EventQueue-1"` at startup | Known and harmless. The window still opens and works. It's printed by the Java runtime while the browser starts, and it doesn't appear in `runner.log`. |
| Console shows `ERROR:chrome\...` lines at startup | Expected. Chromium checks Windows registry keys that don't exist on this machine. These messages don't affect the runner. |
| Console shows `Cannot create directory 'browser/images/...'` | Chromium couldn't unpack a resource folder. Check that the runner's icons and text display correctly. If they don't, report it. |
| The window opens but is blank (black) | The browser can't draw on some virtual machines, because they have no graphics hardware for it. The course's runner works on a physical machine. To see the browser's own log, run `$env:RUNNER_BROWSER_LOG = "1"` and then `.\run.bat`. If the log shows `No task runner for threadId 0`, the browser can't start on that machine. |
| The runner window doesn't open | Check that `lesson-runner\target\classes` exists. If it doesn't, run `mvn -q compile` in `lesson-runner`. |
| The chapters don't load | Check that `parts.json` exists in the repository root, and that the chapter folders are present. |

If a problem isn't listed here, note the step you were on and the full error text, and report it. Include the terminal output, because the first error is often the useful one.
