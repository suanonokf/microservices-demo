FROM eclipse-temurin:22-jre
USER root
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /Service-discovery
COPY target/Service-discovery-0.0.1-SNAPSHOT.jar Service-discovery.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","Service-discovery.jar"]