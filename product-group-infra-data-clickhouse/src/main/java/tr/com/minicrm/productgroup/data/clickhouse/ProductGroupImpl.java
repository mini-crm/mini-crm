package tr.com.minicrm.productgroup.data.clickhouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import tr.com.minicrm.productgroup.data.ProductGroup;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupImpl implements ProductGroup {

  private Long id;

  private String name;

  private int version;

  public ProductGroupImpl(String name) {
    super();
    this.name = name;
  }

}
