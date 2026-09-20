FROM eclipse-temurin:22-jre
USER root
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /AI SERVICE
COPY target/AI-Service-0.0.1-SNAPSHOT.jar AI-Service.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","AI-Service.jar"]