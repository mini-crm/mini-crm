package tr.com.minicrm.productgroup.data.service;

import tr.com.minicrm.productgroup.data.ProductGroup;

public interface ProductGroupDataService {

  void save(ProductGroup entity);

  ProductGroup findByName(String name);

  ProductGroup findById(Long id);

  void update(ProductGroup entity);

}
