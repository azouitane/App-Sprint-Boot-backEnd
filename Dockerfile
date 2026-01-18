# syntax=docker/dockerfile:1

################################################################################
# Stage 1: Download dependencies (cache Maven)
################################################################################
FROM eclipse-temurin:21-jdk-jammy AS deps

WORKDIR /build

COPY mvnw mvnw
COPY .mvn/ .mvn/
RUN chmod +x mvnw

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -DskipTests

################################################################################
# Stage 2: Build application
################################################################################
FROM deps AS package

WORKDIR /build
COPY src/ src/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/*SNAPSHOT.jar target/app.jar

################################################################################
# Stage 3: Extract Spring Boot layers
################################################################################
FROM package AS extract

WORKDIR /build
RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

################################################################################
# Stage 4: Runtime image (light + secure)
################################################################################
FROM eclipse-temurin:21-jre-jammy AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

USER appuser
WORKDIR /app

COPY --from=extract /build/target/extracted/dependencies/ ./
COPY --from=extract /build/target/extracted/spring-boot-loader/ ./
COPY --from=extract /build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract /build/target/extracted/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
