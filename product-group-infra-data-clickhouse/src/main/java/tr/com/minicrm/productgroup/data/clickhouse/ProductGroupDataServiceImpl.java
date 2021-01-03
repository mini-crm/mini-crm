package tr.com.minicrm.productgroup.data.clickhouse;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

public class ProductGroupDataServiceImpl implements ProductGroupDataService {

  private final SqlSessionFactory sqlSessionFactory;

  public ProductGroupDataServiceImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void save(ProductGroup entity) {
    try (final SqlSession sqlSession = sqlSessionFactory.openSession();) {
      final ProductGroupMapper mapper = sqlSession.getMapper(ProductGroupMapper.class);
      mapper.save((ProductGroupImpl) entity);
      sqlSession.commit(true);
    }
  }

  @Override
  public ProductGroup findByName(String name) {
    ProductGroup result;
    try (final SqlSession sqlSession = sqlSessionFactory.openSession();) {
      final ProductGroupMapper mapper = sqlSession.getMapper(ProductGroupMapper.class);
      result = mapper.findByName(name);
    }
    return result;
  }

  @Override
  public void update(ProductGroup entity) {
    try (final SqlSession sqlSession = sqlSessionFactory.openSession();) {
      final ProductGroupMapper mapper = sqlSession.getMapper(ProductGroupMapper.class);
      mapper.update((ProductGroupImpl) entity);
      sqlSession.commit(true);
    }
  }

  @Override
  public ProductGroup findById(Long id) {
    ProductGroup result;
    try (final SqlSession sqlSession = sqlSessionFactory.openSession();) {
      final ProductGroupMapper mapper = sqlSession.getMapper(ProductGroupMapper.class);
      result = mapper.findById(id);
    }
    return result;
  }

}
