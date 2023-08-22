FROM eclipse-temurin:19
COPY build/libs/*.jar ./
ENTRYPOINT ["java","-jar","/trainDriverHelper.jar"]