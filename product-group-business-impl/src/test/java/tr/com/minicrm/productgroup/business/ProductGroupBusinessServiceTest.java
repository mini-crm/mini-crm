package tr.com.minicrm.productgroup.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductGroupBusinessServiceTest {

  @Test
  void testWhenDuplicateProductGroupNameProvidedThanProductGroupAlreadyExistExceptionShouldReturn() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    FakeProductGroup sample = new FakeProductGroup();
    dataService.addToStore(sample);

    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    Exception exception = Assertions.assertThrows(ProductGroupAlreadyExistException.class, () -> {
      pgbs.createNewProductGroup(sample);
    });
    Assertions.assertTrue(exception.getMessage().contains(sample.getName()));
  }

  @Test
  void testWhenNewProductGroupNameProvidedThanProductGroupDataServiceShouldBeCalled() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    dataService.clear();
    FakeProductGroup sample = new FakeProductGroup();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    pgbs.createNewProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
  }

  @Test
  void testWhenProductGroupUpdatedThanNewNameShouldExistInDatastore() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    dataService.clear();
    FakeProductGroup sample = new FakeProductGroup(1L, "Demo 1");
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    pgbs.createNewProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
    sample = new FakeProductGroup(1L, "Demo 2");
    pgbs.updateProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
  }

  @Test
  void testWhenInvalidProductGroupNameProvidedThanProductGroupDoesNotExistExceptionShouldReturn() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    FakeProductGroup sample = new FakeProductGroup();
    dataService.clear();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    Exception exception = Assertions.assertThrows(ProductGroupDoesNotExistException.class, () -> {
      pgbs.updateProductGroup(sample);
    });
    Assertions.assertTrue(exception.getMessage().contains(sample.getId().toString()));
  }

}
