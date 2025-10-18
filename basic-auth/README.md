# Basic Authentication Demo

A Spring Boot application demonstrating basic authentication implementation using Spring Security.

## Overview

This project showcases a simple Spring Boot application with Spring Security configured for basic authentication. It includes both public and protected endpoints to demonstrate different security levels.

## Features

- **Spring Security Integration**: Configured with Spring Security for authentication
- **Public Endpoint**: Accessible without authentication (`/`)
- **Protected Endpoint**: Requires authentication (`/admin`)
- **Form-based Login**: Default Spring Security login form
- **CSRF Protection**: Disabled for simplicity (not recommended for production)

## Project Structure

```
src/
├── main/
│   ├── java/com/basic/basic_auth/
│   │   ├── BasicAuthApplication.java      # Main Spring Boot application
│   │   ├── config/
│   │   │   └── SecurityConfig.java        # Security configuration
│   │   └── controller/
│   │       └── BasicController.java       # REST endpoints
│   └── resources/
│       └── application.properties         # Application configuration
└── test/
    └── java/com/basic/basic_auth/
        └── BasicAuthApplicationTests.java # Test classes
```

## Configuration

### Security Configuration (`SecurityConfig.java`)

The security configuration includes:
- CSRF protection disabled
- Public access to root endpoint (`/`)
- Authentication required for all other endpoints
- Default form login enabled
- Logout functionality enabled

### Application Properties

- **Application Name**: `basic-auth`
- **Server Port**: `8080`
- **Default User**: `user`
- **Default Password**: `password`

## Endpoints

| Endpoint | Method | Authentication Required | Description |
|----------|--------|----------------------|-------------|
| `/` | GET | No | Public endpoint returning "Hello World" message |
| `/admin` | GET | Yes | Protected endpoint requiring ADMIN role |

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone the repository** (if not already done)
2. **Navigate to the basic-auth directory**:
   ```bash
   cd basic-auth
   ```
3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or on Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

### Testing the Application

1. **Access the public endpoint**:
   - Open your browser and go to `http://localhost:8080/`
   - You should see: "Hello World, this is public endpoint"

2. **Access the protected endpoint**:
   - Go to `http://localhost:8080/admin`
   - You will be redirected to the login page
   - Use credentials:
     - **Username**: `user`
     - **Password**: `password`
   - After successful login, you should see: "This is protected endpoint"

## Security Features Demonstrated

- **Authentication**: Form-based login with default Spring Security login page
- **Authorization**: Role-based access control using `@PreAuthorize`
- **Session Management**: Automatic session handling
- **Logout**: Secure logout functionality

## Dependencies

- **Spring Boot Starter Security**: Core Spring Security functionality
- **Spring Boot Starter Web**: Web application framework
- **Spring Boot Starter Test**: Testing framework
- **Spring Security Test**: Security testing utilities

## Important Notes

⚠️ **Security Considerations**:
- This is a demo application and should not be used in production
- CSRF protection is disabled for simplicity
- Default credentials are hardcoded
- No password encoding is implemented

## Next Steps

To enhance this application, consider:
- Implementing proper password encoding
- Adding user registration functionality
- Enabling CSRF protection
- Adding database integration for user management
- Implementing JWT tokens for stateless authentication

## Troubleshooting

### Common Issues

1. **Port already in use**: Change the port in `application.properties`
2. **Authentication fails**: Verify the default credentials (`user`/`password`)
3. **Access denied**: Ensure you're logged in before accessing protected endpoints

### Logs

Check the console output for detailed logs and any error messages during startup or runtime.
