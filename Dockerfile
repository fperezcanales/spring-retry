ARG gradleVersion=7.2.0-jdk17-alpine

## Stage 1 gradle build
FROM gradle:${gradleVersion} AS build
# COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /app
COPY . .
RUN gradle build

RUN ls -la

FROM openjdk:17.0.1-jdk-slim

EXPOSE 8080

# Non Root User Configuration
RUN groupadd -r -g 10001 appGrp \
    && useradd -r -u 10000 -s /sbin/nologin -d /opt/app/ -G appGrp app

USER 10000
RUN ls -la /app/build/libs/

COPY --from=build /app/build/libs/spring-retry-1.0.jar /opt/app/app.jar

#  "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom",
ENTRYPOINT ["java","-jar","/app/app.jar"]