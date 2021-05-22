#!/bin/bash
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy3.json localhost:9000/api/seed/add
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy3.json localhost:9000/api/seed/add
echo "Get all"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/seeds
echo "Get 1"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/seed/1
echo "Get update"
curl -X PUT -H "Content-Type: application/json" -d @./resource/dummy3Update.json http://localhost:9000/api/seed/update/1
echo "Get delete"
curl -i -X DELETE localhost:9000/api/seed/delete/1