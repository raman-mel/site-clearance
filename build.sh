#!/bin/bash
set -e

mvn clean install

which docker

if [ $? -eq 0 ]
then
    docker build -t site-clearance-simulator .
fi