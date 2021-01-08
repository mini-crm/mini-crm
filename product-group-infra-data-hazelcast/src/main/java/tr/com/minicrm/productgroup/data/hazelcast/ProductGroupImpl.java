package tr.com.minicrm.productgroup.data.hazelcast;

import java.io.Serializable;

import tr.com.minicrm.productgroup.data.ProductGroup;

public class ProductGroupImpl implements ProductGroup, Serializable {

  private Long id;

  private String name;

  private int version;

  public ProductGroupImpl(Long id, String name, int version) {
    super();
    this.id = id;
    this.name = name;
    this.version = version;
  }

  public ProductGroupImpl(String name) {
    super();
    this.name = name;
  }

  public ProductGroupImpl() {
    super();
  }

  public ProductGroupImpl(ProductGroup productGroup) {
    this.name = productGroup.getName();
    this.id = productGroup.getId();
    this.version = productGroup.getVersion() + 1;
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
    return version;
  }
}
