# Keycloak Authentication with Spring Boot

This project demonstrates how to integrate **Keycloak** (an open-source identity and access management solution) with a **Spring Boot** application using OAuth2/OpenID Connect for authentication.

## 🚀 Features

- **OAuth2/OpenID Connect Integration** with Keycloak
- **Spring Security** for authentication and authorization
- **Thymeleaf** templates for web pages
- **Docker Compose** for easy Keycloak setup
- **User Information Display** showing logged-in user credentials

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/auth/keycloak_auth/
│   │   ├── config/
│   │   │   └── SecurityConfig.java          # Spring Security configuration
│   │   ├── controllers/
│   │   │   └── AppController.java           # REST endpoints
│   │   └── KeycloakAuthApplication.java     # Main application class
│   └── resources/
│       ├── application.properties           # Application configuration
│       └── templates/
│           ├── home.html                    # Public home page
│           └── menu.html                    # Protected menu page
├── docker-compose.yml                       # Keycloak container setup
└── pom.xml                                  # Maven dependencies
```

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Security 6.5.5**
- **Thymeleaf** (template engine)
- **Keycloak** (identity provider)
- **Docker & Docker Compose**
- **Maven** (build tool)

## 📋 Prerequisites

Before running this project, make sure you have:

- **Java 17** or higher
- **Maven 3.6+**
- **Docker** and **Docker Compose**
- **Git** (optional, for cloning)

## 🚀 How to Run Locally

### Step 1: Clone the Repository

```bash
git clone <your-repository-url>
cd keycloak-auth
```

### Step 2: Start Keycloak

Start the Keycloak server using Docker Compose:

```bash
docker compose up -d
```

This will start Keycloak on `http://localhost:8080` with the following default credentials:
- **Admin Username**: `admin`
- **Admin Password**: `admin`

### Step 3: Configure Keycloak Client

1. Open Keycloak Admin Console: `http://localhost:8080`
2. Login with admin credentials
3. Navigate to **Clients** → **Create**
4. Create a new client with these settings:
   - **Client ID**: `keycloak-test-client`
   - **Client Protocol**: `openid-connect`
   - **Root URL**: `http://localhost:8082`
   - **Valid Redirect URIs**: `http://localhost:8082/login/oauth2/code/keycloak`
   - **Web Origins**: `http://localhost:8082`
5. Go to **Credentials** tab and copy the **Client Secret**
6. Update `application.properties` with the client secret

### Step 4: Update Configuration

Edit `src/main/resources/application.properties` and update the client secret:

```properties
spring.security.oauth2.client.registration.keycloak.client-secret=YOUR_CLIENT_SECRET_HERE
```

### Step 5: Run the Spring Boot Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8082`

## 🌐 Application Endpoints

| Endpoint | Description | Authentication Required |
|----------|-------------|------------------------|
| `GET /` | Public home page | No |
| `GET /private` | Protected menu page with user info | Yes |
| `GET /oauth2/authorization/keycloak` | OAuth2 login redirect | No |

## 🔐 Authentication Flow

1. **User visits** `http://localhost:8082/private`
2. **Spring Security** redirects to Keycloak login
3. **User authenticates** with Keycloak credentials
4. **Keycloak redirects back** to the application
5. **Application displays** user information on the menu page

## 👤 User Information Display

When authenticated, the `/private` endpoint displays:
- **Full Name** from Keycloak
- **Email Address** from Keycloak
- **Username** from Keycloak

## 🐳 Docker Configuration

The `docker-compose.yml` file sets up:
- **Keycloak** on port 8080
- **PostgreSQL** database for Keycloak
- **Persistent volumes** for data storage

## 🔧 Configuration Details

### Application Properties

```properties
# Server Configuration
server.port=8082

# OAuth2 Client Configuration
spring.security.oauth2.client.registration.keycloak.client-id=keycloak-test-client
spring.security.oauth2.client.registration.keycloak.client-secret=YOUR_SECRET
spring.security.oauth2.client.registration.keycloak.scope=openid,profile,email
spring.security.oauth2.client.registration.keycloak.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.keycloak.authorization-grant-type=authorization_code

# Keycloak Provider Configuration
spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8080/realms/master
```

### Security Configuration

The `SecurityConfig.java` configures:
- **OAuth2 Login** with Keycloak
- **Public endpoints** (home page)
- **Protected endpoints** (private pages)
- **Logout functionality**

## 🧪 Testing the Application

1. **Visit the home page**: `http://localhost:8082`
2. **Click "Login with Keycloak"** or visit `http://localhost:8082/private`
3. **Login with Keycloak credentials**
4. **View your user information** on the menu page

## 🛠️ Troubleshooting

### Common Issues

1. **Port 8082 already in use**:
   ```bash
   lsof -ti:8082 | xargs kill
   ```

2. **Keycloak realm not found**:
   - Ensure Keycloak is running: `docker compose ps`
   - Check the realm name in `application.properties`

3. **Client not found in Keycloak**:
   - Verify client ID and secret in `application.properties`
   - Check redirect URI configuration in Keycloak

### Debug Mode

Enable debug logging by adding to `application.properties`:
```properties
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.security.oauth2=DEBUG
```

## 📚 Additional Resources

- [Spring Security OAuth2 Documentation](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)
- [Keycloak Documentation](https://www.keycloak.org/documentation)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
