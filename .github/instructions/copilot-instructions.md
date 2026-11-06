---
applyTo: "**/*"
---

# GitHub Copilot Instructions - DataBank Development Standards

## Purpose
This file defines how GitHub Copilot should generate code for DataBank IMX development teams.
All generated code must align with internal standards for quality, maintainability, security, and consistency.

## Scope
This repository is a **Java training repository**. Follow the **Common Standards** section and the **Java Project Instructions** section for all code generation in this repository.

---

## Common Standards

### General Principles
- Prioritize **readability, maintainability, and clarity** over brevity.
- Follow **DataBank coding standards** at all times.
- Assume code will be **reviewed, tested, and deployed to client environments**.
- Avoid unsafe, experimental, or non-production-ready patterns.
- Do not introduce unnecessary dependencies or frameworks.
- Third-party packages **must** have licenses that allow closed-source commercial use.
- Prefer existing project patterns over introducing new architectural or library choices.

### Architecture and Design
- Prefer clear separation of concerns.
- Avoid tightly coupled designs.
- Design for reuse and extensibility.
- Keep controllers, endpoints, and entry points thin; place business logic in services or domain components.
- Match the structure and conventions already used by the repository unless the user explicitly requests a redesign.

### Error Handling and Security
- Validate all inputs.
- Avoid exposing sensitive information in exceptions, logs, API responses, or console output.
- Follow least-privilege principles.
- Do not generate insecure defaults, hardcoded secrets, or bypass validation/authentication/authorization checks.
- Exception and error messages must be clear, concise, grammatically correct, and end with a period.

### Comments and Documentation
- Write comments only when necessary.
- Use clear, concise US-English.
- Document complex logic, non-obvious decisions, and important operational behavior.
- Avoid comments that merely restate the code.

### Source Control and Workflow
- All code must be tracked in source control.
- Commit early and often.
- Use meaningful commit messages.
- Link commits to tasks, issues, bugs, or enhancements where appropriate.
- Use branches and pull requests for review; do not commit directly to protected mainline branches.

### Code Reviews and Quality Gates
- All code must be reviewed before merge or delivery.
- Address correctness, readability, performance, security, and maintainability.
- Where applicable, code must pass SonarQube or other required quality gates before merge.

### Testing and QA Expectations
- Follow a **test-driven mindset** where practical.
- Ensure new code is testable.
- Include unit tests and integration tests where appropriate.
- Consider edge cases, invalid inputs, failure scenarios, and regression risk.

### Prohibited Practices
- Do NOT generate hardcoded credentials, secrets, tokens, or connection strings.
- Do NOT introduce unapproved third-party libraries.
- Do NOT expose internal implementation details unnecessarily.
- Do NOT produce code that violates DataBank IP, security, or delivery policies.

---

## Java Project Instructions

### General Style

- Write idiomatic, readable modern Java (JDK 25).
- Prefer explicit, maintainable code over compact or clever code.
- Use descriptive names. Follow standard Java conventions: `PascalCase` for types, `camelCase` for methods and variables, `UPPER_SNAKE_CASE` for constants.
- Match the repository's existing layout and conventions.
- Follow the repository's formatting and linting setup when present.

### Structure

- Keep classes focused and cohesive.
- Keep methods reasonably small and single-purpose.
- Place business logic outside of `main` methods, UI handlers, and other entry points when practical.
- Reuse existing utilities and patterns before introducing new abstractions.
- Prefer records, immutability, and interfaces where they fit.

### Error Handling

- Throw and catch specific exceptions instead of broad `Exception` or `Throwable` where practical.
- Preserve the original exception as the cause when wrapping or translating errors.
- Keep user-facing error messages clear and safe.
- Never swallow exceptions silently.
- Use try-with-resources for anything that implements `AutoCloseable`.

### Logging

- Use the repository's existing approved logging pattern.
- If the repository does not define a logging abstraction, use `java.util.logging` or SLF4J rather than `System.out` for operational logging.
- Use appropriate log levels.
- Never log secrets, credentials, tokens, or sensitive customer data.

### Documentation

- Add Javadoc to public types and methods when the repository uses it consistently or when behavior is not obvious.
- Keep inline comments minimal and purposeful.

### Testing

- Unit tests **must** use **JUnit 5** (Jupiter).
- Avoid introducing alternate Java test frameworks unless already required by the repository.
- Include coverage for normal flow, validation failures, edge cases, and exception paths as appropriate.

### Build

- The lesson runner is built with Maven (`lesson-runner/pom.xml`) and targets JDK 25.

### Training Repository Notes

- This repository contains training content. Lesson files and try-it-yourself files are teaching material,
  not production code. Do not apply production hardening standards to files under the `ch*` chapter folders.
- Scott's lesson and try-it-yourself files follow the established training conventions for this repository.
- Lesson code intentionally uses simple constructs (for example `System.out` and single-file programs) for teaching.

---

## Copilot Behavior Guidelines

When generating code, Copilot should:
- Follow all applicable rules in this document automatically.
- Prefer existing project patterns over new ones.
- Match surrounding code style closely.
- Avoid speculative implementations.
- Ask for clarification through comments or generated TODO notes only when requirements are genuinely unclear.

---

## Definition of Done

Generated code is acceptable only if it:
- Runs successfully without warnings in the target project context.
- Passes required static analysis and quality gates where applicable.
- Follows the Java standards in this file.
- Includes appropriate validation, error handling, and logging.
- Is readable, maintainable, and testable.
