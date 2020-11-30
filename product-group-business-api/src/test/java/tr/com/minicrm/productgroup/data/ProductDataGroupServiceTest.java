package tr.com.minicrm.productgroup.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductDataGroupServiceTest {

	@Test
	void testWhenProductGroupSavedThanItShouldExistInDataStore() {
		FakeProductGroupDataService service = new FakeProductGroupDataService();
		ProductGroupEntity entity = new FakeProductGroupEntity(1L);
		service.save(entity);
		service.hasId(entity.getId());
	}

	@Test
	void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
		FakeProductGroupDataService service = new FakeProductGroupDataService();
		ProductGroupEntity entity = new FakeProductGroupEntity(2l);
		service.save(entity);
		Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
			service.save(entity);
		});
		Assertions.assertEquals("Product group with " + entity.getName() + " already defined", exception.getMessage());
	}

}
