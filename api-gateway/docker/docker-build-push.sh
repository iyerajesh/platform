#!/usr/bin/env bash

./mvnw clean install
docker build -f docker/Dockerfile --no-cache -t gcr.io/xylia-platform/api-gateway:2.0.1 .
docker push gcr.io/xylia-platform/api-gateway:2.0.1

# docker run --name api-gateway  -p 9090:9090 -d gcr.io/xylia-platform/api-gateway:2.0.1