package tr.com.minicrm.web.productgroup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.web.generated.productgroup.api.ProductGroupApi;
import tr.com.minicrm.web.generated.productgroup.model.FindProductGroupQuery;
import tr.com.minicrm.web.generated.productgroup.model.FindProductGroupQueryOperationResponse;
import tr.com.minicrm.web.generated.productgroup.model.NewProductGroup;
import tr.com.minicrm.web.generated.productgroup.model.OperationResponse;

@RestController
public class ProductGroupController implements ProductGroupApi {

  @Autowired
  private ProductGroupBusinessService service;

  @Override
  public ResponseEntity<OperationResponse> createNewProductGroup(@Valid NewProductGroup newProductGroup) {
    service.createNewProductGroup(new ProductGroup() {

      @Override
      public int getVersion() {
        return 0;
      }

      @Override
      public String getName() {
        return newProductGroup.getName();
      }

      @Override
      public Long getId() {
        return null;
      }
    });
    return new ResponseEntity<OperationResponse>(new OperationResponse().code("0").message("OK"), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FindProductGroupQueryOperationResponse> findProductGroupByName(
      @Valid FindProductGroupQuery findProductGroupQuery) {
    ProductGroup findByName = service.findByName(findProductGroupQuery.getName());
    FindProductGroupQueryOperationResponse response = new FindProductGroupQueryOperationResponse()
        .body(new tr.com.minicrm.web.generated.productgroup.model.ProductGroup().id(findByName.getId())
            .name(findByName.getName()).version(findByName.getVersion())).code("0").message("OK");
    return new ResponseEntity<FindProductGroupQueryOperationResponse>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<OperationResponse> updateProductGroup(
      tr.com.minicrm.web.generated.productgroup.model.@Valid ProductGroup productGroup) {
    service.updateProductGroup(new ProductGroup() {
      @Override
      public int getVersion() {
        return productGroup.getVersion();
      }

      @Override
      public String getName() {
        return productGroup.getName();
      }

      @Override
      public Long getId() {
        return productGroup.getId();
      }
    });
    return new ResponseEntity<OperationResponse>(new OperationResponse().code("0").message("OK"), HttpStatus.OK);
  }

}
