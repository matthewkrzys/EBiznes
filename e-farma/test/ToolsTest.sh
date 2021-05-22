#!/bin/bash
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy2.json localhost:9000/api/tool/add
echo "Get add"
curl -i -H "Content-Type: application/json" -X POST -d @./resource/dummy2.json localhost:9000/api/tool/add
echo "Get all"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/tools
echo "Get 1"
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET localhost:9000/api/tool/1
echo "Get update"
curl -X PUT -H "Content-Type: application/json" -d @./resource/dummy2Update.json http://localhost:9000/api/tool/update/1
echo "Get delete"
curl -i -X DELETE localhost:9000/api/tool/delete/1