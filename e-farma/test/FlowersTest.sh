#!/bin/bash
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy4.json localhost:9000/api/flower/add
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy4.json localhost:9000/api/flower/add
echo "Get all"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/flowers
echo "Get 1"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/flower/1
echo "Get update"
curl -X PUT -H "Content-Type: application/json" -d @./resource/dummy4Update.json http://localhost:9000/api/flower/update/1
echo "Get delete"
curl -i -X DELETE localhost:9000/api/flower/delete/1