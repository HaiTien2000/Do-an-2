#!/bin/bash

echo "Building Car Showroom Management System..."

# Clean previous build
echo "Cleaning previous build..."
rm -rf target/

# Build with Maven
echo "Building with Maven..."
mvn clean install

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "Build completed successfully!"
    echo "JAR file created at: target/car-showroom-management-1.0.0.jar"
else
    echo "Build failed!"
    exit 1
fi

echo ""
echo "To run the application:"
echo "java -jar target/car-showroom-management-1.0.0.jar"
echo "" 