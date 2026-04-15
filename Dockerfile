### dockerfile per render.com

FROM eclipse-temurin:26-jdk AS build

WORKDIR /build

RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:26-jdk

WORKDIR /app
COPY --from=build /build/target/quarkus-app/lib/ /app/lib/
COPY --from=build /build/target/quarkus-app/*.jar /app/
COPY --from=build /build/target/quarkus-app/app/ /app/app/
COPY --from=build /build/target/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/quarkus-run.jar"]