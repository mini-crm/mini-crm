package tr.com.minicrm.productgroup.business;

import java.util.HashMap;
import java.util.Map;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;

public class FakeProductGroupDataService implements ProductGroupDataService {

	private Map<String, ProductGroup> groups = new HashMap<>();

	@Override
	public void save(ProductGroup entity) {
		groups.put(entity.getName(), entity);
	}

	@Override
	public ProductGroup findByName(String name) {
		return groups.get(name);
	}

	public void addToStore(ProductGroup sample) {
		groups.put(sample.getName(), sample);

	}

	public void clear() {
		groups.clear();
	}

}
