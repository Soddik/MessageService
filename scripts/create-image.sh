#!/bin/bash
IMAGE_NAME=slevyns/message-service

pushd ../../

mvn clean install

docker build -t $IMAGE_NAME

docker image ls | grep $IMAGE_NAME