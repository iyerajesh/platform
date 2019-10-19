#!/usr/bin/env bash

./mvnw clean install
docker build -f docker/Dockerfile --no-cache -t iyerajesh/discovery:latest .
docker push iyerajesh/discovery:latest

# docker run --name discovery -p 8888:8888 -d iyerajesh/discovery:latest