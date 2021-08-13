#!/bin/bash
set -e

mvn clean install
mvn pmd:check
mvn spotbugs:check

which docker

if [ $? -eq 0 ]
then
    docker build -t aconex-simulator .
fi