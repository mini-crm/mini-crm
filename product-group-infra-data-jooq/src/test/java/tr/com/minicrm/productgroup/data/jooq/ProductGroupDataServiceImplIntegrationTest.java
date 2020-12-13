package tr.com.minicrm.productgroup.data.jooq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {

  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroupImpl saved = new ProductGroupImpl("Demo");
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroupImpl saved = new ProductGroupImpl("Demo2");
    service.save(saved);
    Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      service.save(saved);
    });
    Assertions.assertEquals("Product group with " + saved.getName() + " already defined", exception.getMessage());
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroup saved = new ProductGroupImpl("Demo2");
    service.save(saved);
    saved = service.findByName(saved.getName());
    ProductGroup toBeUpdated = new ProductGroupImpl(saved.getId(), "Demo3", saved.getVersion());
    service.update(toBeUpdated);
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }

}
