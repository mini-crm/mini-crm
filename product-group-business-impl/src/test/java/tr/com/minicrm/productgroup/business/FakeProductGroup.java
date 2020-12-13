package tr.com.minicrm.productgroup.business;

import tr.com.minicrm.productgroup.data.ProductGroup;

public class FakeProductGroup implements ProductGroup {

  private Long id;
  private String name;

  public FakeProductGroup() {
    super();
    this.id = 1L;
    this.name = "Demo";
  }

  public FakeProductGroup(Long id, String name) {
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
