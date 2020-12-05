package tr.com.minicrm.productgroup.data.jooq;

import tr.com.minicrm.productgroup.data.ProductGroup;

public class ProductGroupImpl implements ProductGroup {

	private Long id;

	private String name;

	public ProductGroupImpl(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProductGroupImpl(String name) {
		super();
		this.name = name;
	}

	public ProductGroupImpl() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

}
