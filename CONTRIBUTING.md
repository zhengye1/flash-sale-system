# Contributing

## Branch names

`<type>/<issue-number>-<short-description>`

- `<type>`: same as the commit type below
- Example: `chore/12-issue-pr-templates`

## Commit messages

`<type>(<scope>): <description>`

| Type       | When to use                                    | 
|------------|------------------------------------------------|
| `feat`     | Add a new feature                              |
| `fix`      | Fix a bug                                      |
| `refactor` | Refactor the code but not change functionality |
| `test`     | Add/Modify test case related                   |
| `docs`     | Documentation                                  |
| `perf`     | Improve performance                            |
| `ci`       | GitHub Actions workflows                       | 
| `chore`    | Build, Docker, dependencies, tooling           |

- Scope is optional, e.g. `backend`, `frontend`, `auth`
- Body explains what and why is changed

Example:

```
chore(backend): run the backend with docker compose

- Add a multi-stage `Dockerfile`: the runtime image has only
  the JRE, with no JDK, Maven or source code, so it is smaller
  and has a smaller attack surface
- Add `.dockerignore` to ignore `target/`, `.env`
  and `.idea/`
- Add an `app` service to compose,
  waits until `postgres` is healthy,
  port binds to `127.0.0.1:8080` so only this machine can reach it
- Pass the DB password via `env_file` to avoid
  actual password in the image
- Make the DB host configurable: compose sets it to `postgres`,
  local runs default to `127.0.0.1`

Closes #14
```
