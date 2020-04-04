FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/Blarne/message-sender-service

FROM maven:3.6.3-jdk-11 as build
WORKDIR /app
COPY --from=clone /app/message-sender-service /app
RUN mvn install

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/message-sender-service-1.0.0-SNAPSHOT.jar /app
EXPOSE 2206
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar message-sender-service-1.0.0-SNAPSHOT.jar"]