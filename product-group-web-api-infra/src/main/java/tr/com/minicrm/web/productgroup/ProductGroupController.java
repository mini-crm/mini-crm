package tr.com.minicrm.web.productgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.ProductGroup;

@RestController
@RequestMapping(value = "/product-group", consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ProductGroupController {

  @Autowired
  private ProductGroupBusinessService service;

  @PostMapping("/create")
  public ResponseEntity<ProductGroupModel> create(@RequestBody ProductGroupModel req) {
    service.createNewProductGroup(new ProductGroup() {

      @Override
      public String getName() {
        return req.getName();
      }

      @Override
      public Long getId() {
        return req.getId();
      }

      @Override
      public int getVersion() {
        return 0;
      }
    });
    ProductGroup saved = service.findByName(req.getName());
    return ResponseEntity.ok(new ProductGroupModel(saved.getName(), saved.getId()));
  }

}
