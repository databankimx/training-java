# Quick Start

This guide takes you from a fresh copy of the repository to a working lesson runner.

---

## Where to clone the repository

Clone the repository under `C:\Development`, for example:

```
C:\Development\training\training-java
```

The lesson runner builds and runs lesson examples inside the repository. Security software on managed machines is configured to allow operations under `C:\Development`. A copy in other locations, such as your Downloads or a temporary folder, may have issues with file operations.

---

## Prerequisites

You need two tools installed on your machine. The setup script checks for them and reports if they're missing.

| Tool | Version | Default Install Path |
|---|---|---|
| **JDK** (Eclipse Temurin) | 25 | `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot` |
| **Apache Maven** | 3.10.0 | `C:\Tools\apache-maven-3.10.0` |

### Install with winget (easiest)

If you have [Windows Package Manager](https://github.com/microsoft/winget-cli) installed, use these commands:

```pwsh
winget install -e --id EclipseAdoptium.Temurin.25.JDK
winget install -e --id ApacheFoundation.Maven
```

### Install manually

Alternatively, download and install manually to the default paths shown above:

- **JDK 25**: Download from [adoptium.net/temurin](https://adoptium.org/temurin/releases/) and install to `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`
- **Maven 3.10.0**: Download from [maven.apache.org](https://maven.apache.org/download.cgi) and install to `C:\Tools\apache-maven-3.10.0`

The setup script looks for them at these exact paths.

---

## Quick setup

From the repository root in PowerShell:

```pwsh
powershell -ExecutionPolicy Bypass -File .\setup.ps1
```

The script:
1. Checks for JDK 25 and Maven 3.10.0
2. Verifies the repository structure
3. Builds the lesson runner with Maven
4. Prints a summary

If any tool is missing, the script reports which one and where to install it.

---

## Start the lesson runner

After setup succeeds, start the runner from the repository root:

```pwsh
cd lesson-runner
.\run.bat
```

A window titled **Lesson Runner** opens. The chapters appear on the left. Click a chapter to see its steps, then click a step to open it.

---

## Try your first step

1. Click **Ch01 · Getting Started** on the left
2. Click **01-hello-world** under the chapter
3. The step opens with a lesson and a **Source** tab showing `Main.java`
4. Press **Run** (or **F5**)
5. The Output pane shows the program's output: `Hello, World!`

Congratulations! Your setup is working.

---

## Running and testing steps

| Action | How |
|---|---|
| **Run a step** | Click the step, then press **Run** or **F5**, or click the Run button. Output appears in the Output pane. |
| **Edit code** | Click the **Source** tab, edit `Main.java`, and press **Run** to test your changes. |
| **Reset edits** | Click **Reset** to restore the original `Main.java`. |
| **Stop a run** | Press **Escape** or click **Stop**. |
| **Test a step** | Press **Test** or click the Test button (if the step has tests). |
| **View input** | If a program asks for input, type in the input box below the Output pane and press Enter. |

---

## Keyboard shortcuts

| Key | Action |
|---|---|
| **F5** | Run the current step |
| **Escape** | Stop a running program |

---

## Rebuilding after changes

If you modify the runner's Java code, rebuild it:

```pwsh
cd lesson-runner
mvn -q compile
```

Then restart `run.bat`.

---

## Troubleshooting

| Issue | What to do |
|---|---|
| **Setup says JDK 25 is missing** | Install using winget: `winget install -e --id EclipseAdoptium.Temurin.25.JDK`. Or download from [adoptium.net](https://adoptium.org/temurin/releases/) and install to `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`. Then run setup.ps1 again. |
| **Setup says Maven is missing** | Install using winget: `winget install -e --id ApacheFoundation.Maven`. Or download from [maven.apache.org](https://maven.apache.org/download.cgi) and install to `C:\Tools\apache-maven-3.10.0`. Then run setup.ps1 again. |
| **Maven build fails** | Check that the JDK path is exactly as shown above. Run `mvn -q compile` in `lesson-runner` to see the full error. |
| **run.bat says "java.exe not found"** | Verify the JDK is installed at `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`. The batch file uses that exact path. |
| **run.bat says "cannot find java"** | Close any other instances of the lesson runner. The port 47800 might be in use. Restart the runner. |
| **"Only output from first run appears"** | The web server persists data between runs at a fixed port. This is intentional. Close and restart the runner to reset the browser state. |
| **The runner window doesn't open** | Check that `lesson-runner\target\classes` exists (the Maven build output). If it doesn't, run `mvn -q compile` again. |
| **The chapters don't load** | Check that `parts.json` exists in the repository root and that chapter folders (ch01-getting-started, ch02-variables-and-input, etc.) are present. |

For other issues, check the Output pane in the runner for error messages, or consult [README.md](README.md).

---

## Next steps

- Read [README.md](README.md) for an overview of the course structure and how to use the runner.
- See [AUTHORING.md](AUTHORING.md) for how to create or modify lesson steps (if you're an instructor).
