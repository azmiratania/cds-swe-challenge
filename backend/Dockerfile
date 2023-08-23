FROM maven:3.8.5-openjdk-17 as builder

WORKDIR /app

COPY src src
COPY pom.xml .

RUN mvn -f ./pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-alpine

COPY --from=builder /app/target/challenge-0.0.1-SNAPSHOT.jar /usr/app/challenge-0.0.1-SNAPSHOT.jar

EXPOSE 8080

#ENTRYPOINT 

# start app
CMD ["java", "-jar", "/usr/app/challenge-0.0.1-SNAPSHOT.jar"]


