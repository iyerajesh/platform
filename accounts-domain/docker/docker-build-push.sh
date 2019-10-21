#!/usr/bin/env bash

./gradlew clean build
docker build -f docker/Dockerfile --no-cache -t iyerajesh/accounts-domain:latest .
docker push iyerajesh/accounts-domain:latest

# docker run --name accounts-domain -d iyerajesh/accounts-domain:latest