package tr.com.minicrm.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "tr.com.minicrm")
@Import(ProductGroupBusinessServiceConfiguration.class)
public class MainConfiguration {


}
