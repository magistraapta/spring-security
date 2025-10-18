# Keycloak Authentication Demo

A Spring Boot application demonstrating OAuth2/OIDC authentication integration with Keycloak identity provider.

## Overview

This project showcases a Spring Boot application with Spring Security configured for OAuth2/OIDC authentication using Keycloak as the identity provider. It includes both public and protected endpoints with a web-based UI using Thymeleaf templates.

## Features

- **OAuth2/OIDC Integration**: Configured with Keycloak for authentication
- **Spring Security**: OAuth2 client and resource server configuration
- **Web UI**: Thymeleaf-based templates for user interface
- **Public Endpoint**: Accessible without authentication (`/`)
- **Protected Endpoint**: Requires authentication (`/private`)
- **User Information Display**: Shows authenticated user details
- **Proper Logout**: OIDC logout with redirect to Keycloak

## Project Structure

```
src/
├── main/
│   ├── java/com/auth/keycloak_auth/
│   │   ├── KeycloakAuthApplication.java    # Main Spring Boot application
│   │   ├── config/
│   │   │   └── SecurityConfig.java         # OAuth2 security configuration
│   │   └── controllers/
│   │       └── AppController.java          # Web controllers
│   └── resources/
│       ├── application.properties          # Application configuration
│       └── templates/
│           ├── home.html                   # Public page template
│           └── menu.html                   # Protected page template
└── test/
    └── java/com/auth/keycloak_auth/
        └── KeycloakAuthApplicationTests.java # Test classes
```

## Configuration

### Security Configuration (`SecurityConfig.java`)

The security configuration includes:
- OAuth2 login with Keycloak
- Public access to root endpoint (`/`)
- Authentication required for all other endpoints
- OIDC logout handler with proper redirect
- Session invalidation on logout

### Application Properties

- **Application Name**: `keycloak-auth`
- **Server Port**: `8082`
- **Keycloak Client ID**: `keycloak-test-client`
- **Keycloak Realm**: `keycloak-test-realm`
- **Keycloak Server**: `http://localhost:8080`

### Keycloak Configuration

The application is configured to work with a Keycloak instance running on `localhost:8080` with:
- **Realm**: `keycloak-test-realm`
- **Client**: `keycloak-test-client`
- **Scopes**: `openid`, `profile`, `email`

## Prerequisites

### Required Software

- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (for Keycloak)

### Keycloak Setup

The project includes a `docker-compose.yml` file to run Keycloak locally:

```yaml
services:
  keycloak:
    image: quay.io/keycloak/keycloak:latest
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8080:8080"
    command:
      - start-dev
```

## Getting Started

### 1. Start Keycloak

```bash
cd keycloak-auth
docker-compose up -d
```

Wait for Keycloak to start (usually takes 1-2 minutes).

### 2. Configure Keycloak

1. **Access Keycloak Admin Console**:
   - Go to `http://localhost:8080`
   - Login with `admin`/`admin`

2. **Create a Realm**:
   - Click "Create Realm"
   - Name: `keycloak-test-realm`
   - Click "Create"

3. **Create a Client**:
   - Go to "Clients" → "Create Client"
   - Client ID: `keycloak-test-client`
   - Client Protocol: `openid-connect`
   - Root URL: `http://localhost:8082`
   - Valid Redirect URIs: `http://localhost:8082/login/oauth2/code/keycloak`
   - Valid Post Logout Redirect URIs: `http://localhost:8082`
   - Click "Save"

4. **Create a User**:
   - Go to "Users" → "Create new user"
   - Username: `testuser`
   - Email: `test@example.com`
   - First Name: `Test`
   - Last Name: `User`
   - Click "Save"
   - Go to "Credentials" tab → "Set Password"
   - Password: `password123`
   - Temporary: OFF
   - Click "Save"

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

Or on Windows:
```bash
mvnw.cmd spring-boot:run
```

### 4. Test the Application

1. **Access the public endpoint**:
   - Open your browser and go to `http://localhost:8082/`
   - You should see the "Welcome to Food Ordering" page

2. **Access the protected endpoint**:
   - Click "View Menu" or go to `http://localhost:8082/private`
   - You will be redirected to Keycloak login
   - Login with the user you created (`testuser`/`password123`)
   - After successful login, you'll see the menu page with user information

## Endpoints

| Endpoint | Method | Authentication Required | Description |
|----------|--------|----------------------|-------------|
| `/` | GET | No | Public page showing welcome message and user info (if logged in) |
| `/private` | GET | Yes | Protected page showing menu and user information |

## UI Features

### Home Page (`/`)
- Welcome message
- User information display (when authenticated)
- Link to protected menu page

### Menu Page (`/private`)
- Personalized welcome with username
- User information display
- Sample menu items
- Logout button

## Security Features Demonstrated

- **OAuth2/OIDC Authentication**: Industry-standard authentication flow
- **Authorization Code Grant**: Secure authorization flow
- **Token Management**: Automatic token handling
- **Session Management**: Proper session handling
- **Logout Flow**: Complete logout with Keycloak integration
- **User Information**: Access to user claims and attributes

## Dependencies

- **Spring Boot Starter OAuth2 Client**: OAuth2 client functionality
- **Spring Boot Starter OAuth2 Resource Server**: Resource server capabilities
- **Spring Boot Starter Security**: Core Spring Security functionality
- **Spring Boot Starter Web**: Web application framework
- **Spring Boot Starter Thymeleaf**: Template engine for web UI
- **Spring Boot Starter Test**: Testing framework
- **Spring Security Test**: Security testing utilities

## Important Notes

⚠️ **Security Considerations**:
- This is a demo application and should not be used in production
- Client secret is exposed in configuration (use environment variables in production)
- Keycloak is running in development mode (not suitable for production)
- Default admin credentials are used for Keycloak

🔧 **Configuration Notes**:
- The application runs on port `8082` to avoid conflicts with Keycloak (`8080`)
- Debug logging is enabled for Spring Security and OAuth2
- Client secret is hardcoded for demo purposes

## Troubleshooting

### Common Issues

1. **Keycloak not accessible**:
   - Ensure Docker is running
   - Check if Keycloak container is up: `docker ps`
   - Wait for Keycloak to fully start (check logs: `docker-compose logs`)

2. **Authentication fails**:
   - Verify Keycloak realm and client configuration
   - Check redirect URI matches exactly: `http://localhost:8082/login/oauth2/code/keycloak`
   - Ensure user exists and password is set

3. **Port conflicts**:
   - Change application port in `application.properties` if needed
   - Update Keycloak client configuration accordingly

4. **Logout issues**:
   - Verify post-logout redirect URI in Keycloak client settings
   - Check that logout URL is: `http://localhost:8082`

### Logs

Check the console output for detailed logs:
- Spring Security debug logs show authentication flow
- OAuth2 debug logs show token exchange process

## Next Steps

To enhance this application, consider:
- Adding role-based authorization
- Implementing API endpoints with JWT validation
- Adding user profile management
- Integrating with a database
- Adding more sophisticated UI styling
- Implementing proper error handling
- Adding unit and integration tests

## Related Projects

This project is part of a Spring Security learning series that includes:
- `basic-auth`: Basic authentication demo
- `microservice-oauth2`: Microservices with OAuth2