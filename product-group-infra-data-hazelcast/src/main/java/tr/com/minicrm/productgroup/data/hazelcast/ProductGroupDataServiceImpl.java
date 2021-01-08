package tr.com.minicrm.productgroup.data.hazelcast;

import java.util.Collection;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

public class ProductGroupDataServiceImpl implements ProductGroupDataService {

  private final HazelcastInstance hazelcastInstance;

  public ProductGroupDataServiceImpl(HazelcastInstance hazelcastInstance) {
    this.hazelcastInstance = hazelcastInstance;
  }

  @Override
  public void save(ProductGroup entity) {
    IMap<String, ProductGroup> map = hazelcastInstance.getMap("hazelcastMap");
    map.put(entity.getName(), entity);
  }

  @Override
  public ProductGroup findByName(String name) {
    IMap<String, ProductGroup> map = hazelcastInstance.getMap("hazelcastMap");
    Predicate predicate = Predicates.equal("name", name);
    Collection<ProductGroup> values = map.values(predicate);
    return values.stream().findAny().get();
  }

  @Override
  public ProductGroup findById(Long id) {
    IMap<String, ProductGroup> map = hazelcastInstance.getMap("hazelcastMap");
    Predicate predicate = Predicates.equal("id", id);
    Collection<ProductGroup> values = map.values(predicate);
    return values.stream().findFirst().get();
  }

  @Override
  public void update(ProductGroup entity) {
    IMap<String, ProductGroup> map = hazelcastInstance.getMap("hazelcastMap");
    var updatedProductGroup = new ProductGroupImpl(entity);
    map.set(updatedProductGroup.getName(), updatedProductGroup);
  }
}
