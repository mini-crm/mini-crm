package tr.com.minicrm.web.platform.configuration;

import java.io.Reader;

import lombok.SneakyThrows;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.data.clickhouse.ProductGroupDataServiceImpl;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@Configuration
@ConditionalOnProperty(value = "platform.datasource.databaseType", havingValue = "clickhouse")
public class ClickHouseConfiguration {

  @SneakyThrows
  @Bean
  SqlSessionFactory sqlSessionFactory() {
    Reader reader = Resources.getResourceAsReader("./config/mybatis-config.xml");
    return new SqlSessionFactoryBuilder().build(reader);
  }

  @Bean
  ProductGroupDataService productGroupDataService() {
    return new ProductGroupDataServiceImpl(sqlSessionFactory());
  }
}
