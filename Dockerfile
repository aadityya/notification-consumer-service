FROM openjdk:17-oracle
ADD target/notification-consumer-with-binder.jar notification-consumer-with-binder.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","notification-consumer-with-binder.jar"]