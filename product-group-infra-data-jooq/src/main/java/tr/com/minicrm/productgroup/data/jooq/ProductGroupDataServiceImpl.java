package tr.com.minicrm.productgroup.data.jooq;

import static tr.com.minicrm.productgroup.data.jooq.generated.tables.ProductGroupTable.PRODUCT_GROUP_TABLE;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.RecordMapper;
import org.jooq.exception.DataAccessException;

import tr.com.minicrm.productgroup.data.ProductGroup;
import tr.com.minicrm.productgroup.data.ProductGroupDataService;
import tr.com.minicrm.productgroup.data.ProductGroupNameIsNotUniqueException;

public class ProductGroupDataServiceImpl implements ProductGroupDataService {

	private DSLContext dslContext;

	public ProductGroupDataServiceImpl(DSLContext dslContext) {
		super();
		this.dslContext = dslContext;
	}

	@Override
	public void save(ProductGroup entity) {
		try {
			dslContext.insertInto(PRODUCT_GROUP_TABLE, PRODUCT_GROUP_TABLE.GROUP_NAME).values(entity.getName())
					.execute();
		} catch (DataAccessException dae) {
			if (ExceptionUtils.isSQLIntegrityConstraintViolationException(dae))
				throw new ProductGroupNameIsNotUniqueException(entity.getName(), dae);
			else
				throw dae;
		}
	}

	@Override
	public ProductGroup findByName(String name) {
		List<ProductGroup> items = dslContext
				.select(PRODUCT_GROUP_TABLE.PGR_ID, PRODUCT_GROUP_TABLE.GROUP_NAME, PRODUCT_GROUP_TABLE.VERSION)
				.from(PRODUCT_GROUP_TABLE).where(PRODUCT_GROUP_TABLE.GROUP_NAME.eq(name))
				.fetch(new RecordMapper<Record3<Long, String, Integer>, ProductGroup>() {
					@Override
					public ProductGroup map(Record3<Long, String, Integer> rec) {
						return new ProductGroupImpl(rec.component1(), rec.component2(), rec.component3());
					}
				});
		if (items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}

}
