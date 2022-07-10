#!/bin/bash

response=$(curl -sS http://localhost:8080/q/openapi?format=json)
echo $response > openapi.json