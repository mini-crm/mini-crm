package tr.com.minicrm.productgroup.data.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {

  @AfterAll
  static void removeCollection() {
    for (var collectionName : mongoTemplate.getCollectionNames()) {
      mongoTemplate.dropCollection(collectionName);
    }
  }

  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = new ProductGroupImpl("Demo");
    saved.setId(sequenceGeneratorService.generateSequence(ProductGroupImpl.sequenceName));
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = new ProductGroupImpl("Demo14");
    saved.setId(sequenceGeneratorService.generateSequence(ProductGroupImpl.sequenceName));
    service.save(saved);

    Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      var saveDuplicate = new ProductGroupImpl("Demo14");
      saveDuplicate.setId(sequenceGeneratorService.generateSequence(ProductGroupImpl.sequenceName));
      service.save(saveDuplicate);
    });
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = new ProductGroupImpl("Demo2");
    saved.setId(sequenceGeneratorService.generateSequence(ProductGroupImpl.sequenceName));
    service.save(saved);
    ProductGroup found = service.findByName(saved.getName());
    ProductGroup toBeUpdated = new ProductGroupImpl(found.getId(), "Demo3", found.getVersion());
    service.update(toBeUpdated);
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }
}
