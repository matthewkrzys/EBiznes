#!/bin/bash
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy1.json localhost:9000/api/fruit/add
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy1.json localhost:9000/api/fruit/add
echo "Get all"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/fruits
echo "Get 1"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/fruit/1
echo "Get update"
curl -X PUT -H "Content-Type: application/json" -d @./resource/dummy1Update.json http://localhost:9000/api/fruit/update/1
echo "Get delete"
curl -i -X DELETE localhost:9000/api/fruit/delete/1