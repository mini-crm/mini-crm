package tr.com.minicrm.productgroup.data.couchbase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.couchbase.exception.ApiException;
import tr.com.minicrm.productgroup.data.couchbase.exception.ErrorType;
import tr.com.minicrm.productgroup.data.couchbase.repository.ProductGroupRepository;
import tr.com.minicrm.productgroup.data.exceptions.ProductGroupNameIsNotUniqueException;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@Service
@Qualifier("ProductGroupDataServiceImpl")
public class ProductGroupDataServiceImpl implements ProductGroupDataService {

  private static final String DESIGN_DOC = "productgroup";

  private CouchbaseTemplate template;

  ProductGroupRepository repository;

  ProductGroupImpl productGroupImpl;

  public ProductGroupDataServiceImpl(CouchbaseTemplate template) {
    super();
    this.template = template;
  }

  @Override
  public void save(ProductGroup productGroup) {
    try {
      ProductGroupImpl save = new ProductGroupImpl(productGroup);
      template.insert(save);
    } catch (Exception exception) {
      if (ExceptionUtils.isSqlIntegrityConstraintViolationException(exception)) {
        throw new ProductGroupNameIsNotUniqueException(productGroup.getName(), exception);
      } else {
        throw exception;
      }
    }
  }

  @Override
  public ProductGroup findByName(String name) {
    final String cleanName = name.toLowerCase().trim();
    return repository.findProductWithName(cleanName);
  }

  @Override
  public ProductGroup findById(Long id) {
    Optional<ProductGroupImpl> productObj = Optional
        .ofNullable(template.findById(id.toString(), ProductGroupImpl.class));
    return productObj.orElseThrow(() -> new ApiException(ErrorType.PRODUCT_NOT_FOUND));
  }

  @Override
  public void update(ProductGroup productGroup) {
    final ProductGroupImpl existingUser = (ProductGroupImpl) this.findById(productGroup.getId());
    if (!(productGroup.getName() == null || productGroup.getName().trim().length() == 0)) {
      existingUser.setName(productGroup.getName());
    }
    if (productGroup.getId() != null) {
      existingUser.setId(productGroup.getId());
    }
    if (productGroup.getVersion() != 0) {
      existingUser.setVersion(productGroup.getVersion());
    }
    save(existingUser);
  }
}
