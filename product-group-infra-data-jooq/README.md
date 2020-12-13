# product-group-infra-data

# How to generate JOOQ code
1. Make your changes on `/product-group-infra-data-jooq/src/main/resources/database-change-log.xml` liquiebase change log file.
2. run `gradle generateJooqJavaCode` command.