# Oracle Site Clearance Simulator

## Technical specs
- Java 8
- Maven 3

### Static Analysis
- PMD
- Spotbugs

### Unit Tests
- JUnit 5
- Jacoco for test-coverage report

## Building locally (you will need Java 8 and Maven)
```
mvn clean install
```

OR running build.sh which will also generate the docker image

```
./build.sh
```

## Running Simulator
`java -jar target/site-clearance-1.0.jar src/main/resources/siteMap.txt`

## Using docker to run simulator

`docker run -it -v <LOCAL_PATH_OF_DIRETORY>:/tmp site-clearance-simulator:latest /tmp/<FILE_NAME>`

e.g. 

`docker run -it -v /Users/rsingh/projects/resources/:/tmp site-clearance-simulator:latest /tmp/siteMap.txt`

## Author
Ramanjit Singh
