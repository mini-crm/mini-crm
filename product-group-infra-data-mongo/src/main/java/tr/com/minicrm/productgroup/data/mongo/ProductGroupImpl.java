package tr.com.minicrm.productgroup.data.mongo;

import lombok.Builder;

import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import tr.com.minicrm.productgroup.data.ProductGroup;

@Document(value = "product_group")
@Builder
public class ProductGroupImpl implements ProductGroup {

  public static final String sequenceName="PGR_ID";

  @Id
  private Long id;

  @Indexed(unique = true)
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVersion(int version) {
    this.version = version;
  }
}
