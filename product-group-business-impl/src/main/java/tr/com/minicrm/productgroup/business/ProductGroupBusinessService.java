package tr.com.minicrm.productgroup.business;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;

public class ProductGroupBusinessService {

  private ProductGroupDataService productGroupDataService;

  public ProductGroupBusinessService(ProductGroupDataService productGroupDataService) {
    super();
    this.productGroupDataService = productGroupDataService;
  }

  public void createNewProductGroup(ProductGroup productGroup) {
    ProductGroup queried = productGroupDataService.findByName(productGroup.getName());
    if (null != queried) {
      throw new ProductGroupAlreadyExistException(productGroup);
    }
    productGroupDataService.save(productGroup);
  }

  public void updateProductGroup(ProductGroup productGroup) {
    ProductGroup queried = productGroupDataService.findById(productGroup.getId());
    if (null == queried) {
      throw new ProductGroupDoesNotExistException(productGroup.getId());
    }
    productGroupDataService.update(productGroup);
  }

  public ProductGroup findByName(String name) {
    return productGroupDataService.findByName(name);
  }

}
