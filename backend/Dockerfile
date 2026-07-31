FROM eclipse-temurin:26-jdk-alpine AS builder
ENV RELEASE=26

WORKDIR /opt/build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted
RUN $JAVA_HOME/bin/jlink \
    --add-modules `jdeps --ignore-missing-deps -q --recursive --multi-release ${RELEASE} --print-module-deps -cp 'extracted/dependencies/lib/*' application.jar` \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --output jdk

# FROM bellsoft/liberica-openjre-debian:latest-cds

FROM alpine:3.24.1
ARG BUILD_PATH=/opt/build

RUN apk add --no-cache git

ENV JAVA_HOME=/opt/jdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

RUN addgroup -g 1000 spring-app && adduser -D -u 1000 -G spring-app spring-app
USER spring-app:spring-app

WORKDIR /opt/workspace


COPY --from=builder $BUILD_PATH/jdk $JAVA_HOME
COPY --from=builder $BUILD_PATH/extracted/snapshot-dependencies ./
COPY --from=builder $BUILD_PATH/extracted/spring-boot-loader/ ./
COPY --from=builder $BUILD_PATH/extracted/dependencies/ ./
COPY --from=builder $BUILD_PATH/extracted/application/ ./

# RUN java -XX:AOTCacheOutput=app.aot -Dspring.context.exit=onRefresh -jar application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]