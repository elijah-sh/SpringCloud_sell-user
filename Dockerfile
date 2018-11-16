FROM hub.c.163.com/library/java:openjdk-8-jdk-alpine

MAINTAINER shenshuiahu shen_shuaihu@163.com

ADD  server/target/user-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]