package tr.com.minicrm.productgroup.data;

import java.util.HashMap;
import java.util.Map;

public class FakeProductGroupDataService implements ProductGroupDataService {

	private Map<Long, ProductGroup> database = new HashMap<>();

	@Override
	public void save(ProductGroup entity) {
		boolean hasData = database.values().stream().anyMatch(it -> it.getName().equals(entity.getName()));
		if (hasData) {
			throw new ProductGroupNameIsNotUniqueException(entity.getName());
		}
		database.put(entity.getId(), entity);
	}

	@Override
	public ProductGroup findByName(String name) {
		return database.values().stream().filter(it -> it.getName().equals(name)).findFirst().get();
	}

}
