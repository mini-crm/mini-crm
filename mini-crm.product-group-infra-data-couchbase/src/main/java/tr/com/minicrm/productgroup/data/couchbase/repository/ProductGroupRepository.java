package tr.com.minicrm.productgroup.data.couchbase.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.couchbase.ProductGroupImpl;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "productGroup")
public interface ProductGroupRepository extends CrudRepository<ProductGroupImpl, Integer> {

  @Query("SELECT u.id, u.name, META(u).id AS _ID, META(u).cas AS _CAS FROM users u "
      + "WHERE u.type = 'tr.com.minicrm.productgroup.data.couchbase.ProductGroupDataServiceImpl' "
      + "AND LOWER(u.name) LIKE '%' || $name || '%'")
  ProductGroup findProductWithName(@Param("name") String name);


}
