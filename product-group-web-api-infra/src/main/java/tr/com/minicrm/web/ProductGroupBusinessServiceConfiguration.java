package tr.com.minicrm.web;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.mysql.ProductGroupDataServiceImpl;

@Configuration
public class ProductGroupBusinessServiceConfiguration {

  @Value("${platform.datasource.databaseType}")
  private String databaseType;

  @Autowired
  private DataSource dataSource;

  @Bean
  ProductGroupBusinessService productGroupBusinessService(ProductGroupDataService productGroupDataService) {
    return new ProductGroupBusinessService(productGroupDataService);
  }

  @Bean
  ProductGroupDataService productGroupDataService(DSLContext dslContext) {
    if (ConfigurationConstants.MYSQL.equalsIgnoreCase(databaseType)) {
      return new ProductGroupDataServiceImpl(dslContext);
    } else if (ConfigurationConstants.POSTGRESQL.equalsIgnoreCase(databaseType)) {
      return new tr.com.minicrm.productgroup.data.postgresql.ProductGroupDataServiceImpl(dslContext);
    } else {
      throw new IllegalArgumentException(databaseType + " is not valid");
    }
  }

  @Bean
  DSLContext dslContext() {
    if (ConfigurationConstants.MYSQL.equalsIgnoreCase(databaseType)) {
      return DSL.using(dataSource, SQLDialect.MYSQL);
    } else if (ConfigurationConstants.POSTGRESQL.equalsIgnoreCase(databaseType)) {
      return DSL.using(dataSource, SQLDialect.POSTGRES);
    } else {
      throw new IllegalArgumentException(databaseType + " is not valid");
    }
  }

}
