package tr.com.minicrm.productgroup.data.mongo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {

  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = ProductGroupImpl.builder().name("Demo").build();
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = ProductGroupImpl.builder().name("Demo14").build();
    service.save(saved);

    Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      var saveDuplicate = ProductGroupImpl.builder().name("Demo14").build();
      service.save(saveDuplicate);
    });
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(mongoTemplate, sequenceGeneratorService);
    ProductGroupImpl saved = ProductGroupImpl.builder().name("Demo2").build();
    service.save(saved);
    ProductGroup found = service.findByName(saved.getName());
    ProductGroup toBeUpdated = new ProductGroupImpl(found.getId(), "Demo3", found.getVersion());
    service.update(toBeUpdated);
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }
}
