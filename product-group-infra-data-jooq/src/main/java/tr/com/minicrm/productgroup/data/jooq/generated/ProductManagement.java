/*
 * This file is generated by jOOQ.
 */
package tr.com.minicrm.productgroup.data.jooq.generated;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import tr.com.minicrm.productgroup.data.jooq.generated.tables.ProductGroupTable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductManagement extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>product_management</code>
     */
    public static final ProductManagement PRODUCT_MANAGEMENT = new ProductManagement();

    /**
     * The table <code>product_management.product_group_table</code>.
     */
    public final ProductGroupTable PRODUCT_GROUP_TABLE = ProductGroupTable.PRODUCT_GROUP_TABLE;

    /**
     * No further instances allowed
     */
    private ProductManagement() {
        super("product_management", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            ProductGroupTable.PRODUCT_GROUP_TABLE);
    }
}
