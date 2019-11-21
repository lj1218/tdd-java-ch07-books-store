#!/usr/bin/env bash

image="vfarcic/books-fe"
container_name="books_store"
args="-e MODE=mock_server -e MOCK_PORT=9002 -p 9001:8080 -p 9002:9002"

docker inspect ${image} -f '{{.Id}}' >/dev/null 2>&1 || docker pull ${image}
docker rm -f ${container_name} >/dev/null 2>&1
docker run -d --name ${container_name} ${args} ${image}
