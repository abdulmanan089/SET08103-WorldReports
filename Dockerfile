# Step 1: Java + Maven wali image use karo
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Step 2: Container ke andar /app folder banao
WORKDIR /app

# Step 3: Saari files copy karo container ke /app folder me
COPY . .

# Step 4: Project ko build karo (jar file banayega)
RUN mvn clean package

# Step 5: Java ke liye ek lightweight image use karo
FROM eclipse-temurin:17-jdk

# Step 6: Runtime ke liye bhi /app folder use hoga
WORKDIR /app

# Step 7: Jo jar file banayi thi, wo copy karo yahan
#COPY --from=build /app/target/WorldReportsApp-1.0-SNAPSHOT.jar app.jar
COPY --from=build /app/target/WorldReportsApp-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
# Step 8: Program chalane wali command
CMD ["java", "-jar", "app.jar"]
