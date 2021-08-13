FROM java:openjdk-8-jdk-alpine

COPY target/site-clearance-1.0.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]