#!/bin/bash
set -e

mvn clean install
mvn pmd:check
mvn spotbugs:check

