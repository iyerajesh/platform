FROM openjdk:8-jdk-alpine
LABEL maintainer="Rajesh Iyer <iyerajesh@gmail.com>"
EXPOSE 8085
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /orders
COPY /build/libs/*.jar /orders/orders.jar
WORKDIR /orders
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=gke", "orders.jar"]
