FROM java:8
LABEL maintainer=“chathuranga.t@gmail.com”
VOLUME /tmp
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]