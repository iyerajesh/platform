FROM openjdk:8-jdk-alpine
LABEL maintainer="Rajesh Iyer <iyerajesh@gmail.com>"
EXPOSE 8888
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /discovery
COPY /target/*.jar /discovery/discovery.jar
WORKDIR /discovery
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "discovery.jar"]
