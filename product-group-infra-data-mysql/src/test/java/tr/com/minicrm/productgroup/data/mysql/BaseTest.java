package tr.com.minicrm.productgroup.data.mysql;

import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MySQLContainer;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class BaseTest {
  private static MySQLContainer mysql;
  private static MysqlDataSource mysqlDS;
  protected static DSLContext context;

  @BeforeAll
  static void setUp() throws Exception {
    prepareDatabaseServer();
    prepareDatasource();
    prepareDatabase();
    prepareDSLContext();
  }

  @AfterAll
  static void tearDown() {
    mysql.stop();
  }

  private static void prepareDatabase() throws DatabaseException, SQLException, LiquibaseException {
    liquibase.database.Database database =
        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(mysqlDS.getConnection()));
    Liquibase liquibase =
        new liquibase.Liquibase("database-change-log.xml", new ClassLoaderResourceAccessor(), database);
    liquibase.update(new Contexts(), new LabelExpression());
  }

  private static void prepareDatabaseServer() {
    mysql = (MySQLContainer) new MySQLContainer("mysql:8.0.22").withDatabaseName("product_management")
        .withUsername("root").withPassword("").withEnv("MYSQL_ROOT_HOST", "%");
    mysql.start();
  }

  private static DSLContext prepareDSLContext() {
    return context = DSL.using(mysqlDS, SQLDialect.MYSQL);
  }

  private static void prepareDatasource() {
    mysqlDS = new MysqlDataSource();
    mysqlDS.setURL(mysql.getJdbcUrl());
    mysqlDS.setUser(mysql.getUsername());
    mysqlDS.setPassword(mysql.getPassword());
  }

}
