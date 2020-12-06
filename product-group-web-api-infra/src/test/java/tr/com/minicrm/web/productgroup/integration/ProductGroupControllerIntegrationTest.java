package tr.com.minicrm.web.productgroup.integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.MysqlDataSource;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import tr.com.minicrm.productgroup.business.ProductGroupBusinessService;
import tr.com.minicrm.productgroup.data.jooq.ProductGroupDataServiceImpl;
import tr.com.minicrm.web.productgroup.ProductGroupModel;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductGroupControllerIntegrationTest {

	private static MySQLContainer mysql;
	private static MysqlDataSource mysqlDS;
	private static DSLContext context;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testWhenNewProductGroupNameProvidedThanProductGroupDataServiceShouldBeCalled() throws Exception {
		this.mockMvc.perform(post("/product-group/create").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(new ProductGroupModel("demo"))))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("demo")));
	}

	@TestConfiguration
	static class ProductGroupInfraDataJooqConfiguration {

		@Bean
		public ProductGroupBusinessService productGroupBusinessService() {
			return new ProductGroupBusinessService(new ProductGroupDataServiceImpl(context));
		}

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

	@BeforeAll
	static void setUp() throws Exception {
		prepareDatabaseServer();
		prepareDatasource();
		prepareDatabase();
		prepareDSLContext();
	}

	@AfterAll
	static void tearDown() {
		mysql.stop();
	}

	private static void prepareDatabase() throws DatabaseException, SQLException, LiquibaseException {
		liquibase.database.Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(mysqlDS.getConnection()));
		Liquibase liquibase = new liquibase.Liquibase("database-change-log.xml", new ClassLoaderResourceAccessor(),
				database);
		liquibase.update(new Contexts(), new LabelExpression());
	}

	private static void prepareDatabaseServer() {
		mysql = (MySQLContainer) new MySQLContainer("mysql:8.0.22").withDatabaseName("product_management")
				.withUsername("root").withPassword("").withEnv("MYSQL_ROOT_HOST", "%");
		mysql.start();
	}

	private static DSLContext prepareDSLContext() {
		return context = DSL.using(mysqlDS, SQLDialect.MYSQL);
	}

	private static void prepareDatasource() {
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(mysql.getJdbcUrl());
		mysqlDS.setUser(mysql.getUsername());
		mysqlDS.setPassword(mysql.getPassword());
	}

}