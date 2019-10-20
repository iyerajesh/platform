FROM openjdk:8-jdk-alpine
LABEL maintainer="Rajesh Iyer <iyerajesh@gmail.com>"
EXPOSE 9090
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /api-gateway
COPY /target/*.jar /api-gateway/api-gateway.jar
WORKDIR /api-gateway
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=gke", "api-gateway.jar"]
