#!/usr/bin/env bash

./gradlew clean build
docker build -f docker/Dockerfile --no-cache -t gcr.io/xylia-platform/orders-domain:2.0.3 .
docker push gcr.io/xylia-platform/orders-domain:2.0.3

# docker run --name orders-domain -d gcr.io/xylia-platform/orders-domain:latest