ARG gradleVersion=7.2.0-jdk17-alpine

## Stage 1 gradle build
FROM gradle:${gradleVersion} AS build

WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app /app
WORKDIR /app

EXPOSE 8080

# Non Root User Configuration
RUN groupadd -r -g 10001 appGrp \
    && useradd -r -u 10000 -s /sbin/nologin -d /opt/app/ -G appGrp app

USER 10000

COPY --from=build /app/build/libs/my-spring-retry-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]