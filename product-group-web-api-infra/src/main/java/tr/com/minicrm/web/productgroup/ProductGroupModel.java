package tr.com.minicrm.web.productgroup;

public class ProductGroupModel {

  private String name;

  private Long id;

  public ProductGroupModel() {
    super();
  }

  public ProductGroupModel(String name) {
    super();
    this.name = name;
  }

  public ProductGroupModel(String name, Long id) {
    super();
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }



}
