package tr.com.minicrm.productgroup.data.mongo;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;
import tr.com.minicrm.productgroup.data.mongo.service.SequenceGeneratorService;

@Service
public class ProductGroupDataServiceImpl implements ProductGroupDataService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private SequenceGeneratorService sequenceGeneratorService;


  public ProductGroupDataServiceImpl(MongoTemplate mongoTemplate) {
    super();
    this.mongoTemplate = mongoTemplate;
  }

  public ProductGroupDataServiceImpl(MongoTemplate mongoTemplate, SequenceGeneratorService sequenceGeneratorService) {
    super();
    this.mongoTemplate = mongoTemplate;
    this.sequenceGeneratorService = sequenceGeneratorService;
  }

  @Override
  public void save(ProductGroup document) {
    try {
      mongoTemplate.save(document);
    } catch (Exception exception) {
      if (ExceptionUtils.isSQLIntegrityConstraintViolationException(exception)) {
        throw new ProductGroupNameIsNotUniqueException(document.getName(), exception);
      } else {
        throw exception;
      }
    }
  }

  @Override
  public ProductGroup findByName(String name) {
    Query queryByName = new Query();
    queryByName.addCriteria(Criteria.where("name").is(name));
    return mongoTemplate.findOne(queryByName, ProductGroupImpl.class);
  }

  @Override
  public ProductGroup findById(Long id) {
    return mongoTemplate.findById(id, ProductGroupImpl.class);
  }

  @Override
  public void update(ProductGroup document) {
    Query queryByIdAndVersion = new Query();
    queryByIdAndVersion.addCriteria(
        Criteria.where("id").is(document.getId()).andOperator(Criteria.where("version").is(document.getVersion())));

    var foundProductGroup = mongoTemplate.findOne(queryByIdAndVersion, ProductGroupImpl.class);

    if (Objects.nonNull(foundProductGroup)) {
      foundProductGroup.setName(document.getName());
      foundProductGroup.setVersion(foundProductGroup.getVersion() + 1);
      mongoTemplate.save(foundProductGroup);
    }
  }
}
