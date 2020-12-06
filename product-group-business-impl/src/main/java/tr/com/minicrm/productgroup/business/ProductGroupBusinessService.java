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


}
