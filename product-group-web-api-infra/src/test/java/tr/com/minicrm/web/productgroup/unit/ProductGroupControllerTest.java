package tr.com.minicrm.web.productgroup.unit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.MysqlDataSource;

import liquibase.integration.spring.SpringLiquibase;
import tr.com.minicrm.productgroup.business.FakeProductGroupDataService;
import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.web.productgroup.ProductGroupModel;

@SpringBootTest(properties = {
		"spring.main.allow-bean-definition-overriding=true",
		"spring.liquibase.enabled=false"
		})
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ProductGroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testWhenNewProductGroupNameProvidedThanProductGroupDataServiceShouldBeCalled() throws Exception {
		this.mockMvc.perform(post("/product-group/create").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(new ProductGroupModel("demo"))))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("demo")));
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@TestConfiguration
	static class InMemoryTestConfiguration {

		@Bean
		public ProductGroupBusinessService productGroupBusinessService(FakeProductGroupDataService service) {
			return new ProductGroupBusinessService(service);
		}
		
		@Bean
		public FakeProductGroupDataService fakeProductGroupDataService() {
			return new FakeProductGroupDataService();
		}
		
		@Bean
		public DataSource dataSource() {
			return new MysqlDataSource();
		}
		
		@Bean
		public SpringLiquibase liquibase(DataSource dataSource) {
		    return null;
		}

	}

}