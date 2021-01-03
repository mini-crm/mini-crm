package tr.com.minicrm.productgroup.data.couchbase;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.couchbase.exception.ApiException;
import tr.com.minicrm.productgroup.data.couchbase.repository.ProductGroupRepository;
import tr.com.minicrm.productgroup.data.service.ProductGroupDataService;

@RunWith(MockitoJUnitRunner.class)
public class ProductGroupDataServiceImplTest {

  private static final Long USER_ID = 1L;

  @Mock
  private ProductGroupRepository repoMock;

  @InjectMocks
  private ProductGroupDataServiceImpl productGroupDataService;

  private ProductGroupImpl productGroupImpl;

  private CouchbaseTemplate couchbaseTemplate;

  @Before
  public void init() {
    productGroupImpl = new ProductGroupImpl();
    productGroupImpl.setId(USER_ID);
    productGroupImpl.setName("enes");
    productGroupImpl.setVersion(1);
  }

  @Test
  public void saveUser() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(couchbaseTemplate);
    ProductGroupImpl saved = ProductGroupImpl.builder().name("test").build();
    service.save(saved);
    ProductGroup productGroup = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), productGroup.getName());
  }

  @Test
  public void ifUserExistsfindById() {
    Mockito.when(repoMock.findById(USER_ID.intValue())).thenReturn(Optional.of(productGroupImpl));

    ProductGroupImpl user = (ProductGroupImpl) productGroupDataService.findById(USER_ID);

    Assert.assertNotNull(user);
    Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
    Mockito.verifyNoMoreInteractions(repoMock);
  }

  @Test
  public void findAndUserExists() {
    Mockito.when(repoMock.findProductWithName(ArgumentMatchers.anyString())).thenReturn(productGroupImpl);

    Assert.assertNotNull(productGroupImpl);
    Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
    Mockito.verifyNoMoreInteractions(repoMock);
  }

  @Test(expected = ApiException.class)
  public void updateUserExists() {
    productGroupDataService.update(productGroupImpl);

    Mockito.verify(repoMock, Mockito.times(1)).findById(ArgumentMatchers.anyInt());
    Mockito.verifyNoMoreInteractions(repoMock);
  }

}
