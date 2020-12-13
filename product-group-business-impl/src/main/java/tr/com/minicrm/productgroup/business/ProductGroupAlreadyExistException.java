package tr.com.minicrm.productgroup.business;

import tr.com.minicrm.productgroup.data.ProductGroup;

public class ProductGroupAlreadyExistException extends RuntimeException {

  private static final long serialVersionUID = 5574629702265446451L;

  public ProductGroupAlreadyExistException(ProductGroup productGroup) {
    super("ProductGroup with name " + productGroup.getName() + " and id " + productGroup.getId() + " is exist.");
  }

}
