FROM eclipse-temurin:22-jre
WORKDIR /Service-discovery
COPY target/Service-discovery-0.0.1-SNAPSHOT.jar Service-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","Service-discovery.jar"]