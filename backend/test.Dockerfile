FROM eclipse-temurin:26 AS builder
ENV RELEASE=26

WORKDIR /opt/build
COPY ./target/archecode-*.jar ./application.jar

RUN java -Djarmode=tools -jar application.jar extract --layers --destination . --force
RUN $JAVA_HOME/bin/jlink \
            --add-modules `jdeps --ignore-missing-deps -q -recursive --multi-release $RELEASE --print-module-deps -cp 'dependencies/lib/*' application.jar` \
            --strip-debug \
            --no-man-pages \
            --no-header-files \
            --compress=2 \
            --output jdk