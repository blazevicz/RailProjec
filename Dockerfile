FROM eclipse-temurin:17
COPY build/libs/*.jar /.
ENTRYPOINT ["java","-jar","/trainDriverHelper.jar"]