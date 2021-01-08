package tr.com.minicrm.productgroup.data.hazelcast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {
  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(hazelcastInstance);
    ProductGroupImpl saved = new ProductGroupImpl(idGenerator.newId(), "Demo", 0);
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(hazelcastInstance);
    ProductGroup saved = new ProductGroupImpl(idGenerator.newId(), "Demo", 0);
    service.save(saved);
    saved = service.findByName(saved.getName());
    ProductGroup toBeUpdated = new ProductGroupImpl(saved.getId(), "Demo3", saved.getVersion());
    service.update(toBeUpdated);
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }

}
