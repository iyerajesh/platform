#!/usr/bin/env bash

./mvnw clean install
docker build -f docker/Dockerfile --no-cache -t gcr.io/xylia-platform/api-gateway .
docker push gcr.io/xylia-platform/api-gateway:latest

# docker run --name api-gateway  -p 9090:9090 -d gcr.io/xylia-platform/api-gateway:latest