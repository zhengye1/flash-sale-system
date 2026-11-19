# Roadmap

A flash-sale (秒殺) e-commerce backend built in phases. Each phase ships a working
vertical slice: backend, tests, and a small React UI where it makes sense.

**Status:** ✅ done · 🚧 in progress · ⬜ not started

| Phase                  | Weeks | Status | Goal                                                     | Key tech                                                                      |
|------------------------|-------|--------|----------------------------------------------------------|-------------------------------------------------------------------------------|
| **P0** Foundation      | 1–3   | ✅     | Local stack, project skeleton, CI, code quality          | Docker Compose, Spring Boot 4.1, Flyway, GitHub Actions, Spotless, React + TS |
| **P1** Auth            | 4–7   | 🚧     | Register / login, protect APIs                           | Spring Security, JWT                                                          |
| **P2** Catalog         | TBD   | ⬜     | Browse and search products, fast reads                   | Redis cache-aside, Postgres full-text search, JaCoCo, SonarCloud              |
| **P3** Orders          | TBD   | ⬜     | Place orders, auto-close unpaid ones, AWS minimal deploy | Order state machine, Snowflake IDs, partial indexes                           |
| **P4** Flash sale core | TBD   | ⬜     | Sell limited stock under heavy load, no overselling      | Redis Lua (atomic stock), Kafka (async orders), 202 + polling, k6 load tests  |
| **P5** Reliability     | TBD   | ⬜     | No lost or duplicate orders                              | Idempotency keys, DLQ, transactional outbox, reconciliation                   |
| **P6** Resilience      | TBD   | ⬜     | Survive slow or failing dependencies                     | Resilience4j (timeout, bulkhead, circuit breaker), rate limiting              |
| **P7** Microservices   | TBD   | ⬜     | Split services, keep data consistent                     | Service split, Saga                                                           |
| **P8** Operations      | TBD   | ⬜     | See and run it like production                           | OpenTelemetry, Prometheus, Grafana, Kubernetes (kind), AWS ECS Fargate        |

**Stretch:** database sharding.

**Schedule:**
Phases from P2 on have no dates yet. They haven't been broken into issues, so any date would be a guess.
Dates are added when a phase is planned.

## Principles

- **Every phase is shippable:** tests pass, CI is green, the README explains how to run it.
- **Decisions are written down:** architecture decisions will live in `docs/adr/`.
- **Measure, don't guess:** performance claims come with load-test numbers.

Progress is tracked on the [project board](https://github.com/zhengye1/flash-sale-system/projects)
and per-phase [milestones](https://github.com/zhengye1/flash-sale-system/milestones).
