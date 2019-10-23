#!/usr/bin/env bash

./gradlew clean build
docker build -f docker/Dockerfile --no-cache -t gcr.io/xylia-platform/accounts-domain:2.0.10 .
docker push gcr.io/xylia-platform/accounts-domain:2.0.10

# docker run --name accounts-domain -d gcr.io/xylia-platform/accounts-domain:latest