# ms-course-quarkus

Example instance consuming the
[`pe.edu.nova.java:nova-quarkus-parent:1.0.0`](https://github.com/ahincho/nova-java-quarkus-parent)
POM. The Quarkus counterpart of
[`instances/ms-course`](../ms-course/pom.xml) (the Spring Boot instance).

## Stack

- Java 25
- Quarkus 3.33.2.1 LTS
- `pe.edu.nova.java.starters:nova-notifications-quarkus-extension:1.1.2`

## Build

```bash
./mvnw clean verify
```

The `verify` phase runs: compile + tests + `quarkus:build`. Output: `target/quarkus-app/quarkus-run.jar`.

## Run

```bash
./mvnw quarkus:dev
```

Then:

```bash
curl http://localhost:8080/api/notifications/email/welcome
# -> {"sent":true,"providerMessageId":"<uuid>","channel":"email"}
```

## How it consumes the parent

```xml
<parent>
    <groupId>pe.edu.nova.java</groupId>
    <artifactId>nova-quarkus-parent</artifactId>
    <version>1.0.0</version>
</parent>
```

The parent provides:
- Java 25 (compiler `--release 25`)
- Quarkus 3.33.2.1 LTS (`io.quarkus.platform:quarkus-bom` pre-imported via `<dependencyManagement>`)
- Maven plugins: compiler 3.14, surefire 3.5.3, failsafe 3.5.3
- The `pe.edu.nova.java.starters:nova-notifications-quarkus-extension:1.1.2` dependency (so the `NotificationFacade` bean is auto-wired by CDI)

## Configuration

`src/main/resources/application.properties`:

```properties
quarkus.http.port=8080
quarkus.http.test-port=8081

nova.notifications.enabled=true
nova.notifications.email.provider=sendgrid
nova.notifications.email.api-key=${SENDGRID_API_KEY:test-api-key-demo}
nova.notifications.email.default-sender=no-reply@example.com
nova.notifications.resilience.max-attempts=1
```

## Related

- [`ahincho/nova-java-quarkus-parent`](https://github.com/ahincho/nova-java-quarkus-parent) — the parent POM consumed by this instance.
- [`ahincho/nova-java-quarkus-archetype`](https://github.com/ahincho/nova-java-quarkus-archetype) — the archetype that can generate similar multi-module instances.
- [`ahincho/nova-java-notifications-quarkus-extension`](https://github.com/ahincho/nova-java-notifications-quarkus-extension) — the notifications adapter that wires `NotificationFacade` as a CDI bean.
- [`instances/ms-course`](../ms-course) — the Spring Boot counterpart.