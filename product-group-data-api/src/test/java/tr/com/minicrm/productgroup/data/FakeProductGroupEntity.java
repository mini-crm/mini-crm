package tr.com.minicrm.productgroup.data;

public class FakeProductGroupEntity implements ProductGroup {
	
	private Long id;
	private String name;

	public FakeProductGroupEntity(Long id) {
		super();
		this.id = id;
		this.name = "Demo";
	}
	
	public FakeProductGroupEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
