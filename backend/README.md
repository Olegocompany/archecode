# ArcheCode Application

Spring Boot application

---

## Требования

- Java 26 (JDK)
- Maven (или включенный `mvnw wrapper`)
- MySQL 8.0+

Убедитесь, что `JAVA_HOME` установлена правильно:
```shell
java -version
```

---

## Запуск

### Использование Maven Wrapper (рекомендуется)

Linux|Unix:
```shell
./mvnw spring-boot:run #Linux|Unix
./mvnw.cmd spring-boot:run #Windows
```

### Использование системного Maven

```shell
mvn spring-boot:run
```

## Build

```shell
./mvnw clean package
```

Запускаемый JAR будет расположен в `target/`
