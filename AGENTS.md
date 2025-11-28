# Repository Guidelines

This repository houses a Minecraft 1.20.1 Fabric mod built with Fabric Loom and Gradle.

## Project Structure & Module Organization
- `src/main/java/com/tacuarita/tacotools`: Core mod entrypoint (`TacoTools`), data generator hook (`TacoToolsDataGenerator`), and mixins under `mixin/`.
- `src/main/resources`: Mod metadata (`fabric.mod.json`), mixin config (`tacotools.mixins.json`), plus assets/data as you add them.
- `build/` and `run/`: Generated outputs (artifacts, dev worlds, logs); they stay untracked and can be recreated via Gradle.

## Build, Test, and Development Commands
- `./gradlew build` (`gradlew.bat build` on Windows): Compile, remap, and package a jar to `build/libs/`.
- `./gradlew runClient`: Launch a dev Minecraft client with this mod on the classpath using the `run/` workspace.
- `./gradlew runDatagen`: Execute the Fabric data generator via `TacoToolsDataGenerator` once providers are registered; outputs land under `src/main/resources` (or your configured directory).
- `./gradlew clean`: Remove build outputs for a fresh rebuild.

## Coding Style & Naming Conventions
- Target Java 17 (see `build.gradle`); prefer 4-space indentation and UTF-8 files.
- Keep the package namespace `com.tacuarita.tacotools`; classes PascalCase, methods/fields lowerCamelCase, constants UPPER_SNAKE_CASE.
- Scope mixins to `mixin/` and declare them in `tacotools.mixins.json`; log via `TacoTools.LOGGER` with concise messages.

## Testing Guidelines
- No automated tests exist yet; add JUnit tests under `src/test/java` for new logic and wire required dependencies in `build.gradle`.
- Use descriptive test names (e.g., `ToolBehaviorTest`) and cover edge cases for mixins or data-generation helpers.
- Run `./gradlew test` (or `./gradlew check`) locally before opening a PR once tests are present.

## Commit & Pull Request Guidelines
- Write commits in the imperative and focused (e.g., “Add shovel ability cooldown”); include context in the body and reference issues (`#id`) when relevant.
- For PRs: summarize changes, list manual test steps (client actions, data-gen outputs), and attach screenshots/video for gameplay-visible updates.
- Keep diffs clean: exclude generated `build/` or `run/` content; update metadata when IDs, versions, or mixins change.

## Security & Configuration Tips
- Do not hardcode secrets or tokens; client config belongs in `run/config` and remains ignored.
- Update version constants in `gradle.properties` when bumping dependencies or Minecraft versions; keep `fabric.mod.json` aligned with code entrypoints and mixin classes.
