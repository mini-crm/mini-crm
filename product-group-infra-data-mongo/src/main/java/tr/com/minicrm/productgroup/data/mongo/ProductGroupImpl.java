package tr.com.minicrm.productgroup.data.mongo;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import tr.com.minicrm.productgroup.data.ProductGroup;

@Document(value = "product_group")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupImpl implements ProductGroup {

  public static final String sequenceName = "PGR_ID";

  @Id
  private Long id;

  @Indexed(unique = true)
  private String name;

  private int version;
}
