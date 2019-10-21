FROM openjdk:8-jdk-alpine
LABEL maintainer="Rajesh Iyer <iyerajesh@gmail.com>"
EXPOSE 8080
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /accounts
COPY /build/libs/*.jar /accounts/accounts.jar
WORKDIR /accounts
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=gke", "accounts.jar"]
