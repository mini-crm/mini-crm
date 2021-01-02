package tr.com.minicrm.web.productgroup.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@Configuration
public class ProductGroupBusinessServiceConfiguration {

  @Bean
  ProductGroupBusinessService productGroupBusinessService(ProductGroupDataService productGroupDataService) {
    return new ProductGroupBusinessService(productGroupDataService);
  }

}
