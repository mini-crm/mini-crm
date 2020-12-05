package tr.com.minicrm.productgroup.data.jooq;

import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;

import com.mysql.cj.jdbc.MysqlDataSource;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;

public class ProductGroupDataServiceImplIntegrationTest {

	private static MySQLContainer mysql;
	private static MysqlDataSource mysqlDS;
	private static DSLContext context;

	@Test
	public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
		ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
		ProductGroupImpl saved = new ProductGroupImpl("Demo");
		service.save(saved);
		ProductGroup queried = service.findByName(saved.getName());
		Assertions.assertEquals(saved.getName(), queried.getName());
	}
	
	@Test
	void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
		ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
		ProductGroupImpl saved = new ProductGroupImpl("Demo");
		service.save(saved);
		Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
			service.save(saved);
		});
		Assertions.assertEquals("Product group with " + saved.getName() + " already defined", exception.getMessage());
	}

	@BeforeAll
	static void setUp() throws Exception {
		prepareDatabaseServer();
		prepareDatasource();
		prepareDatabase();
		prepareDSLContext();
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

	@AfterAll
	static void tearDown() {
		mysql.stop();
	}

}
