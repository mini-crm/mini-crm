## Development Environment Setup
Install Docker Desktop and your favorite database development tool such as DBeaver.

if you use mysql as database server, use following steps to create your database.

1. Start local MySQL Server `docker run --detach --name=mysql-dev-server --env="MYSQL_ROOT_PASSWORD=root" --publish 3306:3306 mysql`
2. Connect to database server with username `root` / password `root` and create database using following script. `CREATE SCHEMA product_management DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;`

if you use Postgres as database server , use following steps to create your database.

1. Start your local Postgres Server `docker run --detach --name postgre-dev-server --env="POSTGRES_PASSWORD=postgres" --publish 5432:5432 postgres`
2. Connect to database server with username `postgres` / password `postgres` and create database using  following script. `CREATE DATABASE product_management`

## Generate controller API from Open API specification
Edit specification file located at `/src/main/resources/product-group.yaml` then run ` gradle openApiGenerate` command.

