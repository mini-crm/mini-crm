package tr.com.minicrm.web;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.jooq.ProductGroupDataServiceImpl;

@Configuration
public class ProductGroupBusinessServiceConfiguration {

  @Autowired
  private DataSource dataSource;

  @Bean
  ProductGroupBusinessService productGroupBusinessService(ProductGroupDataService productGroupDataService) {
    return new ProductGroupBusinessService(productGroupDataService);
  }

  @Bean
  ProductGroupDataService productGroupDataService(DSLContext dslContext) {
    return new ProductGroupDataServiceImpl(dslContext);
  }

  @Bean
  DSLContext dslContext() {
    return DSL.using(dataSource, SQLDialect.MYSQL);
  }

}
