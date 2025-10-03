## Spring Security Examples (Java)

This repository contains two independent Spring Boot applications demonstrating different authentication schemes:

- `basic-auth`: Classic form/basic authentication with an in-memory user
- `keycloak-auth`: OpenID Connect (OIDC) login with Keycloak as the Identity Provider

Both apps are self-contained Maven projects located in their respective folders.

### Prerequisites
- Java 17+
- Maven 3.9+
- Docker (for running Keycloak)

---

## basic-auth
Simple Spring Security setup with a public endpoint and an authenticated area. A default in-memory user is configured via properties.

### How to run
```bash
cd basic-auth
./mvnw spring-boot:run
```

### Configuration
Source: `basic-auth/src/main/resources/application.properties`

- `server.port=8080`
- `spring.security.user.name=user`
- `spring.security.user.password=password`

### Endpoints
- `GET /` — public, returns a plain text greeting
- `GET /admin` — protected, requires role `ADMIN`

Note: The default in-memory user has role `USER`. Accessing `/admin` will return 403 unless you configure a user with role `ADMIN`.

Example login page is provided by Spring Security (form login).

---

## keycloak-auth
Spring Security OAuth2/OIDC login backed by a Keycloak server. Renders Thymeleaf views.

### How to run the app
```bash
cd keycloak-auth
./mvnw spring-boot:run
```

The app runs on `http://localhost:8082` by default.

### How to run Keycloak
```bash
cd keycloak-auth
docker compose up -d
```

This starts Keycloak at `http://localhost:8080` with admin user `admin/admin` (for the Keycloak admin console).

### Keycloak configuration (one-time setup)
After Keycloak is up, open the admin console at `http://localhost:8080` and:
1. Create a realm named `keycloak-test-realm`.
2. Create a confidential client named `keycloak-test-client`.
   - Client type: OpenID Connect
   - Access type: Confidential
   - Client secret: set to the value in `application.properties`
   - Valid redirect URIs: `http://localhost:8082/login/oauth2/code/keycloak`
   - Web origins: `http://localhost:8082`
3. Create a user in the realm and set a password (and verify email if desired).

The app is already configured to use:
- `issuer-uri=http://localhost:8080/realms/keycloak-test-realm`
- `client-id=keycloak-test-client`
- `redirect-uri={baseUrl}/login/oauth2/code/{registrationId}`

### Endpoints
- `GET /` — public page. If authenticated, shows user details.
- `GET /private` — requires authentication; triggers OIDC login with Keycloak.

Logout uses OIDC RP-initiated logout and returns to the application base URL.

---

## Running both projects
- Keycloak runs on port `8080` by default (from `docker-compose.yml`).
- `keycloak-auth` app runs on port `8082`.
- `basic-auth` app runs on port `8080`.

Because both Keycloak and `basic-auth` use port `8080`, you cannot run them simultaneously without changing a port. Options:
- Change `basic-auth` `server.port` to a different value (e.g., `8083`), or
- Map Keycloak to a different host port in `docker-compose.yml` and adjust `issuer-uri` accordingly.

---

## Quick verification
### basic-auth
1. Start the app.
2. Visit `http://localhost:8080/` — should show a public message.
3. Visit `http://localhost:8080/admin` — should redirect to login; default user `user/password` will authenticate but will get 403 without `ADMIN` role.

### keycloak-auth
1. Start Keycloak via Docker and configure realm/client as above.
2. Start the app.
3. Visit `http://localhost:8082/` — public page; if logged in, user details are displayed.
4. Visit `http://localhost:8082/private` — redirects to Keycloak for login; upon success, renders a page with user info.

---

## Modules overview
- `basic-auth/src/main/java/com/basic/basic_auth/controller/BasicController.java`
  - `/` public
  - `/admin` requires `ADMIN` (via `@PreAuthorize("hasRole('ADMIN')")`)
- `basic-auth/src/main/java/com/basic/basic_auth/config/SecurityConfig.java`
  - CSRF disabled, form login enabled, `/` permitted, others authenticated
- `keycloak-auth/src/main/java/com/auth/keycloak_auth/controllers/AppController.java`
  - `/` public view; shows user info if present
  - `/private` authenticated view
- `keycloak-auth/src/main/java/com/auth/keycloak_auth/config/SecurityConfig.java`
  - OIDC login enabled, RP-initiated logout configured

---

## License
MIT (or your chosen license)


