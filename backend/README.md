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

Докер принимает готовый `.jar`, из которого он вытянет требуемые зависимости и т.д..
Нужно упаковать `.jar` без тестов (во избежание проверки работы с базой данных, иначе будет ошибка). Не забудьте указать праивльную точку соединения с БД в контексте Docker в `.env`, чтобы в контейнере соединение успешно произошло!

```shell
./mvnw clean package -DskipTests
```

После этого в корне всего проекта:

```shell
docker-compose up -d --build
```