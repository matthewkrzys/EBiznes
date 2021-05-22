#!/bin/bash
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy4.json localhost:9000/api/plant/add
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy4.json localhost:9000/api/plant/add
echo "Get all"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/plants
echo "Get 1"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/plant/1
echo "Get update"
curl -X PUT -H "Content-Type: application/json" -d @./resource/dummy4Update.json http://localhost:9000/api/plant/update/1
echo "Get delete"
curl -i -X DELETE localhost:9000/api/plant/delete/1