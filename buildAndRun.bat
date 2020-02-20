@echo off
call mvn clean -f service-a/pom.xml package
call mvn clean -f service-b/pom.xml package
call docker-compose build
call docker-compose up --force-recreate