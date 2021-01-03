package tr.com.minicrm.productgroup.data.couchbase;

import com.couchbase.client.java.repository.annotation.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import tr.com.minicrm.productgroup.data.ProductGroup;

@Document
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGroupImpl implements ProductGroup {

  @Field
  @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
  private Long id;

  @Field
  private String name;

  @Field
  private int version;

  public ProductGroupImpl(ProductGroup productGroup) {
    this.id = productGroup.getId();
    this.name = productGroup.getName();
    this.version = productGroup.getVersion();
  }

}
