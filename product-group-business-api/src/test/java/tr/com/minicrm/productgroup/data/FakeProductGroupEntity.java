package tr.com.minicrm.productgroup.data;

public class FakeProductGroupEntity implements ProductGroupEntity {
	
	private Long id;

	public FakeProductGroupEntity(Long id) {
		super();
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return "Demo";
	}

}
