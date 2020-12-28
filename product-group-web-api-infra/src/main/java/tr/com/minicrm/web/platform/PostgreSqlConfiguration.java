package tr.com.minicrm.web.platform;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.postgresql.ProductGroupDataServiceImpl;

@Configuration
@ConditionalOnProperty(value = "platform.datasource.databaseType", havingValue = "postgresql")
public class PostgreSqlConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  DSLContext dslContext() {
    return DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  @Bean
  ProductGroupDataService productGroupDataService(DSLContext dslContext) {
    return new ProductGroupDataServiceImpl(dslContext);
  }
}
