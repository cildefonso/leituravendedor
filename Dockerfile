FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD /target/leituravendedor.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]