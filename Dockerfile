FROM openjdk:17

ADD . /src

WORKDIR /src

RUN ./mvnw package -DskipTests

EXPOSE 8081

ENTRYPOINT ["java","-jar","target/MessageService-0.0.1.jar"]