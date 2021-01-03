package tr.com.minicrm.productgroup.data.clickhouse;

import java.sql.SQLException;

import ru.yandex.clickhouse.ClickHouseStatement;

import lombok.SneakyThrows;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.ClickHouseContainer;

public class BaseTest {

  private static final String CREATE_PRODUCT_GROUP_TABLE_SQL =
      """
          CREATE TABLE default.product_group
          (
              `id`          UInt64,
              `name`        String,
              `version`     UInt16,
              `create_time` Date DEFAULT CAST(now(), 'Date')
          ) ENGINE = ReplacingMergeTree(create_time) partition by tuple() order by (id);
          """;
  private static final ClickHouseContainer clickHouseContainer =
      new ClickHouseContainer("yandex/clickhouse-server:20.3");
  private static PooledDataSource dataSource;
  protected static SqlSessionFactory sqlSessionFactory;

  @SneakyThrows
  @BeforeAll
  static void setUp() {
    prepareDatasource();
    prepareDatabase();
    prepareSqlSessionFactory();
  }

  private static void prepareSqlSessionFactory() {
    TransactionFactory transactionFactory = new JdbcTransactionFactory();
    final Environment environment = new Environment.Builder("mybatis-clickHouse-test")
        .dataSource(dataSource)
        .transactionFactory(transactionFactory).build();
    final Configuration configuration = new Configuration();
    configuration.setEnvironment(environment);
    configuration.addMapper(ProductGroupMapper.class);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
  }


  private static void prepareDatasource() {
    clickHouseContainer.start();
    dataSource = new PooledDataSource();
    dataSource.setDriver(clickHouseContainer.getDriverClassName());
    dataSource.setUrl(clickHouseContainer.getJdbcUrl());
    dataSource.setUsername(clickHouseContainer.getUsername());
    dataSource.setPassword(clickHouseContainer.getPassword());
  }

  private static void prepareDatabase() throws SQLException {
    try (ClickHouseStatement statement = (ClickHouseStatement) dataSource.getConnection()
        .createStatement()) {
      statement.execute(CREATE_PRODUCT_GROUP_TABLE_SQL);
    }
  }

  @AfterAll
  static void tearDown() {
    clickHouseContainer.stop();
  }
}
