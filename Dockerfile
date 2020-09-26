FROM java:8
ADD ./target/CodeChallenge-0.0.1-SNAPSHOT.jar CodeChallenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","CodeChallenge-0.0.1-SNAPSHOT.jar"]

