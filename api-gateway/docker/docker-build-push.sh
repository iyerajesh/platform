#!/usr/bin/env bash

./mvnw clean install
docker build -f docker/Dockerfile --no-cache -t iyerajesh/api-gateway:latest .
docker push iyerajesh/api-gateway:latest

# docker run --name api-gateway  -p 9090:9090 -d iyerajesh/api-gateway:latest