package tr.com.minicrm.productgroup.data.clickhouse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {

  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(sqlSessionFactory);
    ProductGroupImpl saved = new ProductGroupImpl("Demo");
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() throws InterruptedException {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(sqlSessionFactory);
    ProductGroup saved = new ProductGroupImpl("Demo2");
    service.save(saved);
    saved = service.findByName(saved.getName());
    ProductGroup toBeUpdated = new ProductGroupImpl(saved.getId(), "Demo3", saved.getVersion());
    service.update(toBeUpdated);
    Thread.sleep(100);// FIXME clickHouse update is not effective
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }

}
