package tr.com.minicrm.web.productgroup.unit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tr.com.minicrm.productgroup.business.FakeProductGroupDataService;
import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.web.productgroup.ProductGroupModel;

@SpringBootTest
@AutoConfigureMockMvc
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

	}

}