package tr.com.minicrm.productgroup.data.mongo;

import com.mongodb.client.MongoClients;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import tr.com.minicrm.productgroup.data.mongo.service.SequenceGeneratorService;

public class BaseTest {
  private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse("mongo");
  private static final String DEFAULT_TAG = "4.0.10";
  protected static MongoTemplate mongoTemplate;
  protected static SequenceGeneratorService sequenceGeneratorService;
  private static MongoDBContainer mongoDB;

  @BeforeAll
  static void setUp() {
    prepareDatabaseServer();
    mongoTemplate = new MongoTemplate(
        MongoClients.create("mongodb://" + mongoDB.getContainerIpAddress() + ":" + mongoDB.getFirstMappedPort()),
        "product_management");

    IndexOperations indexOps = mongoTemplate.indexOps(ProductGroupImpl.class);
    IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoTemplate.getConverter().getMappingContext());
    resolver.resolveIndexFor(ProductGroupImpl.class).forEach(indexOps::ensureIndex);

    sequenceGeneratorService = new SequenceGeneratorService(mongoTemplate);
  }

  private static void prepareDatabaseServer() {
    mongoDB = new MongoDBContainer(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    mongoDB.start();
  }

  @AfterAll
  static void tearDown() {
    mongoDB.stop();
  }
}
