FROM openjdk:17-oracle
ADD target/notification-consumer.jar notification-consumer.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","notification-consumer.jar"]