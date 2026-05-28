# spring_boot_slack_integration

Spring Boot project that runs **TestNG** tests and generates rich HTML reports via **ExtentReports**.

## Project Structure

```
spring_boot_slack_integration/
├── pom.xml                          # Maven config: Spring Boot 3, TestNG 7.9, ExtentReports 5
├── testng.xml                       # TestNG suite: groups, parallelism, listener wiring
└── src/
    ├── main/java/com/example/spring_boot_slack_integration/
    │   ├── TestingNgApplication.java    # Spring Boot entry point
    │   └── CalculatorService.java       # Sample service under test
    └── test/java/com/example/spring_boot_slack_integration/
        ├── ExtentReportListener.java    # Auto-generates HTML report on each run
        ├── CalculatorServiceTest.java   # Math + validation tests with @DataProvider
        └── SmokeTest.java               # Ordered smoke suite with dependsOnMethods
```

## Run Tests

```bash
mvn test
```

## Run Only a Specific Group

```bash
mvn test -Dgroups=smoke
mvn test -Dgroups=math
mvn test -Dgroups=validation
```

## Reports

After `mvn test`, two report locations are generated:

| Report | Location |
|--------|----------|
| **ExtentReports HTML** (rich, with category + status) | `target/extent-reports/TestReport_<timestamp>.html` |
| **Maven Surefire XML + plain text** | `target/surefire-reports/` |

Open the ExtentReports HTML file directly in any browser — no server needed.

## Run Application

```bash
mvn spring-boot:run
```

Starts on `http://localhost:8081` (configured in `application.properties`).

## Requirements

- Java 17+
- Maven 3.8+
