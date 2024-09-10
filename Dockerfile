FROM openjdk:17
EXPOSE 9090
ADD target/netology-diplom-backend-0.0.1-SNAPSHOT.jar netology-diplom-backend.jar
ENTRYPOINT ["java", "-jar", "netology-diplom-backend.jar"]