# DataBank IMX - Java Developer Training

## License and Support

This repository is provided under the [MIT License](LICENSE). You are free to use, copy, modify, merge, publish, distribute, sublicense, and/or sell the materials in this repository, subject to the terms of that license.

This repository is provided for training and reference purposes only. DataBank IMX does not provide warranties, guarantees, maintenance, troubleshooting, implementation assistance, or technical support for this code or its use. Use it in any way permitted by the MIT License, but you are responsible for evaluating, adapting, testing, and supporting any use of it in your own environment.

---

## What is this repository for?

- Java developer training curriculum for DataBank IMX, covering the language from first programs through object-oriented programming, collections, concurrency, and modern features, with a focus on reading and converting existing Java code
- Chapter-by-chapter lessons, with code you can edit and run, and short quizzes
- A final project: a multi-part application built in stages, with a core track and a bonus track, each with a worked example
- A desktop lesson runner, built with Java and JCEF, that lets you read lessons, edit and run each step, and run its tests

---

## A note on editors

This tutorial uses Visual Studio Code throughout. You are welcome to use whichever IDE you prefer, but that choice assumes you are already comfortable with your editor, including its debugger and build tools. Some lessons give steps specific to VS Code, such as setting a breakpoint or starting the debugger. If you use another IDE, you will need to find the matching steps in its documentation.

The lesson runner, the build, and the tests do not depend on VS Code, so you can work through the course with any editor.

---

## About

- **Author:** [Scott McLean](mailto:smclean@databankimx.com)
- **Purpose:** Internal developer training curriculum for DataBank IMX

---

## DataBank Responsibilities for Custom Code

All DataBank employees, including those working with AI-assisted tools, are bound by the [Development Rules of Engagement](development-rules-of-engagement.md). That document defines the rules for quoting, approving, developing, reviewing, scanning, storing, and delivering custom code for client work.

Key obligations that apply to everyone on every custom code engagement:

- **You own the code you deliver.** This applies equally to hand-written code, AI-assisted code, and AI-generated code.
- **Code must be reviewed before delivery.** Review must confirm that the code works, meets DataBank standards, and is maintainable, and that the developer understands it well enough to explain, debug, and maintain it.
- **Code must be in source control before delivery.** All custom code must be committed to the appropriate DataBank GitHub Enterprise repository before it is delivered to a client.
- **CI scanning must pass before delivery.** Required checks are the standards policy scan, SonarQube, and Snyk.
- **All third-party dependencies must be license-vetted.** The license must permit closed-source commercial use. Copyleft or unclear licensing must be escalated before use.
- **Only approved AI tools may be used.** Use only IT-approved tools through company accounts.
- **Custom code is billable work.** Code must be tied to an approved quote or engagement before commitment or delivery.

See the [Development Rules of Engagement](development-rules-of-engagement.md) for the full policy.

---

## Textbook reference

The lessons are written for this course and mirror the C++ course structure. Lessons incorporate practical Java coding patterns and best practices from the Java community.

---

## Setup and requirements

- **Windows 10 or 11**
- **Eclipse Temurin JDK 25** (or compatible JDK 25): installed at `C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`
- **Apache Maven 3.10.0** or later
- **VS Code** with the Extension Pack for Java (recommended), or any editor you prefer

See [QUICKSTART.md](QUICKSTART.md) for a step-by-step guide.

### Quick start

From the repository root, in PowerShell:

```pwsh
powershell -ExecutionPolicy Bypass -File .\setup.ps1
```

The script checks for JDK 25, Maven 3.10.0, and Git, checks the repository, and builds the runner. Run it from an Administrator PowerShell with `-InstallMissing` to install any missing tools. QUICKSTART.md describes both options. Its steps are: compile the runner; copy the runner's libraries into `lesson-runner\dist\lib`; download the JCEF natives into `resources\vendor\jcef\natives` if they are missing, then check them against the approved hashes in `natives.sha256`; and package the runner, which copies the natives into `lesson-runner\dist\natives`. It needs internet access the first time, to download the natives.

Then start the lesson runner:

```pwsh
cd lesson-runner
.\run.bat
```

### Libraries used by the course

| Library | Where | Purpose | License |
|---|---|---|---|
| Eclipse Temurin JDK 25 | Lessons and runner | Compiles and runs every lesson program | GPL 2.0 with Classpath exception |
| Maven 3.10.0 | Lessons and runner | Builds the lesson runner | Apache 2.0 |
| JCEF (Java Chromium Embedded Framework) `me.friwi:jcefmaven:146.0.10` | Lesson runner | The runner's window and browser integration | Apache 2.0 (with BSD-style Chromium/CEF components) |
| Gson 2.11.0 (through jcefmaven) | Lesson runner | Reads the course catalogue and the runner's messages | Apache 2.0 |
| Chromium/CEF natives (Windows x64) | Lesson runner | Native browser rendering engine for JCEF | BSD-style |
| JUnit 5 | Lessons and tests | Unit testing framework for step tests | EPL-2.0 |
| highlight.js 11.11.1 | Lesson runner (web) | Syntax coloring in the Source and Lesson panes | BSD-3-Clause |
| marked.js | Lesson runner (web) | Converts lesson Markdown to HTML | MIT |
| viz.js | Lesson runner (web) | Renders diagrams in lessons (Graphviz in JavaScript) | MIT |

All third-party libraries listed above have been license-vetted and approved for use under section 12 of the Development Rules of Engagement. The runner also bundles libraries that jcefmaven brings in, such as the Apache Commons, GlueGen, JOGL, and error_prone_annotations jars. They appear in `lesson-runner\dist\lib` after setup, and they still need the same licence review before this statement covers them.

### VS Code

Install the **Extension Pack for Java** (publisher `Microsoft`). It provides Java language support, debugging, and IntelliSense. You can also use other Java IDEs or code editors you are comfortable with.

---

## Running the training

### Lesson Runner

The lesson runner is a desktop window, built with Java and JCEF. It lets you browse chapters, read lessons, edit and run each step, and run its tests. Setup is in [QUICKSTART.md](QUICKSTART.md).

#### Finding lessons

The chapter list on the left shows each chapter, such as *Ch01 · Getting Started*. Click a chapter to show its steps, then click a step to open it.

- Chapters are listed in order. The final project comes after the numbered chapters.
- Each step is one folder. A step with a `src/Main.java` has code you can run.
- Homework appears under its chapter, in its own list.

#### Reading and running a step

- The **Lesson** tab shows the step's lesson, with quizzes.
- The **Source** tab shows the step's `src/Main.java`. It is an editor. Edits are kept in the runner until you click **Reset**, and nothing is written to the repository.
- Press **Run** (or F5) to compile and run the step. The build status lines appear in the Output pane, followed by the program's output.
- Press **Test** to compile the step and run its tests, for steps that have them.
- If a program asks for input, type in the input box under the Output pane, then press Enter or click **Go**.
- Press **Escape**, or click **Stop**, to stop a run. The Run button becomes Stop while a program runs.
- The **Backtrace** checkbox is not available for Java (stack traces already show class, method, and line).

#### Quick picks

Each step's `commands.txt` lists quick picks. Choosing one fills the input box. A pick is either `run` or `test`, optionally followed by arguments for the program.

#### Homework and spoilers

Homework steps ask you to write your own program first. The Run button stays disabled until you choose **Show example (spoiler)**. Once you do, the example is available, and Run is enabled. Try the exercise before you reveal the example, since most of the learning happens while you are stuck.

#### Keyboard shortcuts

| Key | Action |
|---|---|
| F5 | Run the current step |
| Escape | Stop a running program. When nothing is running, Escape discards your unsaved edits to the source. |

#### Troubleshooting

For issues with the runner or lesson steps, see [QUICKSTART.md](QUICKSTART.md).

---

## Third Party Licenses

This section lists all third-party libraries, tools, and components used by the training course, along with their licenses. All licenses have been reviewed and approved for use under section 12 of the DataBank IMX Development Rules of Engagement.

### Build and Runtime

| Component | Version | License | Link | Notes |
|---|---|---|---|---|
| Eclipse Temurin JDK | 25.0.5 | GPL 2.0 with Classpath exception | [eclipse.org/temurin](https://www.eclipse.org/temurin/) | OpenJDK distribution |
| Apache Maven | 3.10.0 | Apache License 2.0 | [maven.apache.org](https://maven.apache.org/) | Build automation |
| Java Chromium Embedded Framework (JCEF) | 146.0.10 | Apache License 2.0 | [github.com/chromiumembedded/java-cef](https://github.com/chromiumembedded/java-cef) | Wrapper for CEF/Chromium |
| Chromium Embedded Framework (CEF) | 146 | BSD-style | [bitbucket.org/chromiumembedded/cef](https://bitbucket.org/chromiumembedded/cef) | Native browser engine (Windows x64) |
| JUnit | 5 | Eclipse Public License 2.0 (EPL-2.0) | [junit.org/junit5](https://junit.org/junit5/) | Testing framework |

### Web Components (Lesson Runner UI)

| Component | Version | License | Link | Notes |
|---|---|---|---|---|
| highlight.js | 11.11.1 | BSD 3-Clause | [highlightjs.org](https://highlightjs.org/) | Syntax highlighting |
| marked.js | Latest | MIT | [marked.js.org](https://marked.js.org/) | Markdown to HTML converter |
| viz.js | Latest | MIT | [github.com/mdaines/viz.js](https://github.com/mdaines/viz.js/) | Graphviz in JavaScript for diagrams |

### License Details

**GPL 2.0 with Classpath exception** (Eclipse Temurin JDK): Permits free use, modification, and distribution. The Classpath exception allows applications to link against the JDK without requiring the application itself to be open-source.

**Apache License 2.0**: Permits free use, modification, and distribution in binary and source forms, provided that copyright notices are retained and significant changes are documented.

**BSD 3-Clause** (highlight.js): Permits free use, modification, and distribution with copyright notice and disclaimer included.

**Eclipse Public License 2.0** (JUnit): Permits free use, modification, and distribution. Secondary licenses may be added.

**MIT** (marked.js, viz.js): Permits free use, modification, and distribution with copyright notice included.

All licenses permit commercial use and closed-source derivatives when used in accordance with their terms.
