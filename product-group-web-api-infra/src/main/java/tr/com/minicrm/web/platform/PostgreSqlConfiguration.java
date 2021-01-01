package tr.com.minicrm.web.platform;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.data.postgresql.ProductGroupDataServiceImpl;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@Configuration
@ConditionalOnProperty(value = "platform.datasource.databaseType", havingValue = "postgresql")

public class PostgreSqlConfiguration {

  @Bean
  DSLContext dslContext(DataSource dataSource) {
    return DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  @Bean
  ProductGroupDataService productGroupDataService(DSLContext dslContext) {
    return new ProductGroupDataServiceImpl(dslContext);
  }
}
