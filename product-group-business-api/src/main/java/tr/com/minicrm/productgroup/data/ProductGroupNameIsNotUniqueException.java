package tr.com.minicrm.productgroup.data;

public class ProductGroupNameIsNotUniqueException extends RuntimeException {

	private static final long serialVersionUID = 4021187441167292318L;

	public ProductGroupNameIsNotUniqueException(String name) {
		super("Product group with " + name + " already defined");
	}

	public ProductGroupNameIsNotUniqueException(String name, Throwable throwable) {
		super("Product group with " + name + " already defined", throwable);
	}
}
