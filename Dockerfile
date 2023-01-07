FROM openjdk:8

EXPOSE 9090

ADD target/blog-app.jar blog-app.jar

ENTRYPOINT ["java","-jar","blog-app.jar"]
