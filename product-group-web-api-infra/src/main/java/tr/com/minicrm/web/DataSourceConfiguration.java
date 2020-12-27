package tr.com.minicrm.web;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

  @Bean
  @ConfigurationProperties("platform.datasource")
  public HikariDataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}
