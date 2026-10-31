# Flash Sale System

## Setup

1. `cp .env.example .env`
2. `git config core.hooksPath .githooks`
3. `docker compose up -d`
4. `cd flash-sale-backend && ./mvnw spring-boot:run`

## Roadmap

See [ROADMAP](docs/ROADMAP.md) for phases, tech, and progress.

## Code Format

Formatting is enforced by Spotless with palantir-java-format.

### Installation

1. In IDEA, navigate to Plugins -> Marketplace, search for `palantir-java-format`, install it
2. After restarting, navigate to Settings -> Other Settings -> palantir-java-format, find the
   `palantir-java-format` setting, enable it because it is disabled by default on installation
3. Navigate to Settings -> Tools -> Actions on Save, enable `Reformat code`
4. Choose some Java file, type something with invalid format such as 5 space tab, and save. IDEA should automatically
   fix the format.

### Maven plugin

- It can also use maven plugin to fix code format issue, using `./mvnw spotless:check` to see which files have format
  issue
- Then run `./mvnw spotless:apply` and let spotless fix automatically
- `./mvnw verify` runs `spotless:check` automatically, so the build fails if code is not formatted.
