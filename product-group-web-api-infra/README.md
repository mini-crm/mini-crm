# product-group-web-api-infra

## Development Environment Setup
1. Install Docker Desktop and MySQL Workbench or another tool.
2. Pull MySQL Server image from docker repository 
`docker pull mysql/mysql-server:latest`
3. Start local MySQL Server 
`docker run --detach --name=mysql-dev-server --env="MYSQL_ROOT_PASSWORD=root" --publish 3306:3306 mysql`
4. Connect to database server and create database using following script.
`CREATE SCHEMA product_management DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;`