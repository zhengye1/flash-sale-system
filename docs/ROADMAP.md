# Roadmap

A flash-sale (秒殺) e-commerce backend built in phases. Each phase ships a working
vertical slice: backend, tests, and a small React UI where it makes sense.

**Status:** ✅ done · 🚧 in progress · ⬜ not started

| Phase | Weeks | Status | Goal | Key tech |
|---|---|---|---|---|
| **P0** Foundation | 1–2 | 🚧 | Local stack, project skeleton, CI, code quality | Docker Compose, Spring Boot 4.1, Flyway, GitHub Actions, Spotless, React + TS |
| **P1** Auth | 3 | ⬜ | Register / login, protect APIs | Spring Security, JWT |
| **P2** Catalog | 4–5 | ⬜ | Browse and search products, fast reads | Redis cache-aside, Postgres full-text search, JaCoCo, SonarCloud |
| **P3** Orders | 6–7 | ⬜ | Place orders, auto-close unpaid ones | Order state machine, Snowflake IDs, partial indexes |
| **P4** Flash sale core | 8–9 | ⬜ | Sell limited stock under heavy load, no overselling | Redis Lua (atomic stock), Kafka (async orders), 202 + polling, k6 load tests |
| **P5** Reliability | 10–11 | ⬜ | No lost or duplicate orders | Idempotency keys, DLQ, transactional outbox, reconciliation |
| **P6** Resilience | 12 | ⬜ | Survive slow or failing dependencies | Resilience4j (timeout, bulkhead, circuit breaker), rate limiting |
| **P7** Microservices | 13–14 | ⬜ | Split services, keep data consistent | Service split, Saga |
| **P8** Operations | 15–16 | ⬜ | See and run it like production | OpenTelemetry, Prometheus, Grafana, Kubernetes (kind), AWS ECS Fargate |

**Stretch:** database sharding.

## Principles

- **Every phase is shippable:** tests pass, CI is green, the README explains how to run it.
- **Decisions are written down:** architecture decisions will live in `docs/adr/`.
- **Measure, don't guess:** performance claims come with load-test numbers.

Progress is tracked on the [project board](https://github.com/zhengye1/flash-sale-system/projects)
and per-phase [milestones](https://github.com/zhengye1/flash-sale-system/milestones).
