package tr.com.minicrm.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import tr.com.minicrm.web.platform.MongoConfiguration;
import tr.com.minicrm.web.platform.MySqlConfiguration;
import tr.com.minicrm.web.platform.PostgreSqlConfiguration;

@Configuration
@ComponentScan(basePackages = "tr.com.minicrm")
@Import({ProductGroupBusinessServiceConfiguration.class, MongoConfiguration.class, MySqlConfiguration.class,
    PostgreSqlConfiguration.class})
public class MainConfiguration {


}
