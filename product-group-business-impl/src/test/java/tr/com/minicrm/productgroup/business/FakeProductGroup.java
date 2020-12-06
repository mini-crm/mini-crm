package tr.com.minicrm.productgroup.business;

import tr.com.minicrm.productgroup.data.ProductGroup;

public class FakeProductGroup implements ProductGroup {
	

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public String getName() {
		return "Demo";
	}

}
