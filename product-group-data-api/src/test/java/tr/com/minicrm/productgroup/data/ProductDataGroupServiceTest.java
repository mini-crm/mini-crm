package tr.com.minicrm.productgroup.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductDataGroupServiceTest {

  @Test
  void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = new FakeProductGroupEntity(1L);
    service.save(entity);
    ProductGroup entityLookup = service.findByName(entity.getName());
    Assertions.assertEquals(entity.getName(), entityLookup.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = new FakeProductGroupEntity(2L);
    service.save(entity);
    Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      service.save(entity);
    });
    Assertions.assertEquals("Product group with " + entity.getName() + " already defined", exception.getMessage());
  }

  @Test
  void testWhenUpdateProductGroupThanItShouldUpdateInDataStore() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = new FakeProductGroupEntity(2L, "Demo 2");
    service.save(entity);
    entity = new FakeProductGroupEntity(2L, "Demo 3");
    service.update(entity);
    ProductGroup entityLookup = service.findByName(entity.getName());
    Assertions.assertEquals(entity.getName(), entityLookup.getName());
  }

}
