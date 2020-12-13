package tr.com.minicrm.productgroup.business;

public class ProductGroupDoesNotExistException extends RuntimeException {

  private static final long serialVersionUID = -394078545391923795L;

  public ProductGroupDoesNotExistException(Long productGroup) {
    super("ProductGroup with id " + productGroup + " does not exist.");
  }
}
