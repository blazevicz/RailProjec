FROM eclipse-temurin:17
COPY . .
ENTRYPOINT ["java","-jar","/build/libs/trainDriverHelper.jar"]