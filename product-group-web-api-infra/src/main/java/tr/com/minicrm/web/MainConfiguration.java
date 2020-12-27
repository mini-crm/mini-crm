package tr.com.minicrm.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan(basePackages = "tr.com.minicrm")
@Import(ProductGroupBusinessServiceConfiguration.class)
public class MainConfiguration {

  @Value("${platform.datasource.databaseType}")
  private String databaseType;

  @Bean
  public SpringLiquibase liquibase(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:db/" + databaseType + "/database-change-log.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }
  
  

}
