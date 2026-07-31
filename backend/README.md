# ArcheCode Application

Spring Boot application

---

## Запуск

### Заполните dotenv

Для ключа `JWT` требуется секретный ключ не менее 32 симовлов

Такой ключ можно создать, например, с помощью `OpenSSL`:
```shell
openssl rand -base64 32 # последнее число - "длина"
```

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

# Docker

Для работы с докером нужно упаковать без тестов

```shell
./mvnw clean package -DskipTests
```