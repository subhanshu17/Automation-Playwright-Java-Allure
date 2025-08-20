# Playwright + Cucumber + TestNG (Java) – Ready-to-run

## Prereqs
- Java 11+
- Maven
- Allure CLI (optional for HTML report)

## Run
```
mvn clean test
```

> First run will download Playwright browsers automatically.

## Allure Report
```
# Build HTML via Maven plugin
mvn allure:report

# Or with CLI
allure generate target/allure-results -o target/allure-report --clean
allure open target/allure-report
```

## Config
Edit `config.properties`:
```
baseUrl=https://www.google.com
headless=false
slowMo=0
```
