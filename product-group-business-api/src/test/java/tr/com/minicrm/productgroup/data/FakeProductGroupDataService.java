package tr.com.minicrm.productgroup.data;

import java.util.HashMap;
import java.util.Map;

public class FakeProductGroupDataService implements ProductDataGroupService {

	private Map<Long, ProductGroupEntity> database = new HashMap<>();

	@Override
	public void save(ProductGroupEntity entity) {
		boolean hasData = database.values().stream().anyMatch(it -> it.getName().equals(entity.getName()));
		if (hasData) {
			throw new ProductGroupNameIsNotUniqueException(entity.getName());
		}
		database.put(entity.getId(), entity);
	}

	public void hasId(Long id) {
		database.containsKey(id);
	}

}
