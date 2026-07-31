FROM bellsoft/liberica-openjre-debian:latest-cds AS builder
# ENV RELEASE=26

WORKDIR /opt/build
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted


FROM bellsoft/liberica-openjre-debian:latest-cds

ARG BUILD_PATH=/opt/build
# ENV JAVA_HOME=/opt/jdk
# ENV PATH="${JAVA_HOME}/bin:${PATH}"

# RUN groupadd --gid 1000 spring-app \
#     && useradd --uid 1000 --gid spring-app --shell /bin/bash --create-home spring-app

# USER spring-app:spring-app
WORKDIR /opt/workspace


# COPY --from=builder $BUILD_PATH/jdk $JAVA_HOME
COPY --from=builder $BUILD_PATH/extracted/snapshot-dependencies ./
COPY --from=builder $BUILD_PATH/extracted/spring-boot-loader/ ./
COPY --from=builder $BUILD_PATH/extracted/dependencies/ ./
COPY --from=builder $BUILD_PATH/extracted/application/ ./

# RUN java -XX:AOTCacheOutput=app.aot -Dspring.context.exit=onRefresh -jar application.jar

ENTRYPOINT ["java", "-XX:AOTCache=app.aot", "-jar", "application.jar"]