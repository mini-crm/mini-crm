package tr.com.minicrm.web.platform;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;

import tr.com.minicrm.productgroup.data.mongo.ProductGroupDataServiceImpl;
import tr.com.minicrm.productgroup.data.mongo.ProductGroupImpl;
import tr.com.minicrm.productgroup.data.mongo.service.SequenceGeneratorService;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@Configuration
@ConditionalOnProperty(value = "platform.datasource.databaseType", havingValue = "mongo")
public class MongoConfiguration {

  @Value("${platform.datasource.jdbcUrl:''}")
  private String jdbcUrl;

  @Value("${platform.datasource.database:''}")
  private String database;

  @Bean
  MongoTemplate mongoTemplate() {
    ConnectionString connectionString = new ConnectionString(jdbcUrl);
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build();
    return new MongoTemplate(MongoClients.create(mongoClientSettings),database);
  }

  @Bean
  SequenceGeneratorService sequenceGeneratorService(MongoTemplate mongoTemplate) {
    IndexOperations indexOps = mongoTemplate.indexOps(ProductGroupImpl.class);
    IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoTemplate.getConverter().getMappingContext());
    resolver.resolveIndexFor(ProductGroupImpl.class).forEach(indexOps::ensureIndex);
    return new SequenceGeneratorService(mongoTemplate);
  }

  @Bean
  ProductGroupDataService productGroupDataService(MongoTemplate mongoTemplate,
      SequenceGeneratorService sequenceGeneratorService) {
    return new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
  }
}
