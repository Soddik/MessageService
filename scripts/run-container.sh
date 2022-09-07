#!/bin/bash

APP_IMAGE_KEY="message-service"
APP_IMAGE_NAME="slevyns/$APP_IMAGE_KEY-image"
APP_CONTAINER_NAME="application"

docker container stop $APP_CONTAINER_NAME
docker container rm $APP_CONTAINER_NAME
docker run --name $APP_CONTAINER_NAME -p 8081:8081 -d $APP_IMAGE_NAME