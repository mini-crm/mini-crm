# product-group-infra-data-mysql

# How to generate JOOQ code
1. Make your changes on `/product-group-infra-data-jooq/src/main/resources/db/mysql/database-change-log.xml` liquiebase change log file.
2. run `gradle generateJooqJavaCode` command.