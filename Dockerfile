# --------- STAGE 1 : Build ---------
FROM gradle:8.9-jdk21-alpine AS build

WORKDIR /app

COPY build.gradle* settings.gradle* gradlew ./
COPY gradle ./gradle

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon || return 0

COPY . .

RUN apk add --no-cache bash git openssh curl ca-certificates

RUN ./gradlew bootJar --no-daemon -Dorg.gradle.jvmargs="-Xmx1024m" --stacktrace --info


# --------- STAGE 2 : Runtime ---------
FROM eclipse-temurin:21-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["--spring.profiles.active=prod"]
