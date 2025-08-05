@echo off
echo Building Car Showroom Management System...

REM Clean previous build
echo Cleaning previous build...
if exist target rmdir /s /q target

REM Build with Maven
echo Building with Maven...
call mvn clean install

REM Check if build was successful
if %ERRORLEVEL% EQU 0 (
    echo Build completed successfully!
    echo JAR file created at: target\car-showroom-management-1.0.0.jar
) else (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo To run the application:
echo java -jar target\car-showroom-management-1.0.0.jar
echo.
pause 