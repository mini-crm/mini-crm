/*
 * This file is generated by jOOQ.
 */
package tr.com.minicrm.productgroup.data.jooq.generated.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import tr.com.minicrm.productgroup.data.jooq.generated.Keys;
import tr.com.minicrm.productgroup.data.jooq.generated.ProductManagement;
import tr.com.minicrm.productgroup.data.jooq.generated.tables.records.ProductGroupTableRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductGroupTable extends TableImpl<ProductGroupTableRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>product_management.product_group_table</code>
     */
    public static final ProductGroupTable PRODUCT_GROUP_TABLE = new ProductGroupTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductGroupTableRecord> getRecordType() {
        return ProductGroupTableRecord.class;
    }

    /**
     * The column <code>product_management.product_group_table.pgr_id</code>.
     */
    public final TableField<ProductGroupTableRecord, Long> PGR_ID = createField(DSL.name("pgr_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>product_management.product_group_table.group_name</code>.
     */
    public final TableField<ProductGroupTableRecord, String> GROUP_NAME = createField(DSL.name("group_name"), SQLDataType.VARCHAR(150).nullable(false), this, "");

    private ProductGroupTable(Name alias, Table<ProductGroupTableRecord> aliased) {
        this(alias, aliased, null);
    }

    private ProductGroupTable(Name alias, Table<ProductGroupTableRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>product_management.product_group_table</code> table reference
     */
    public ProductGroupTable(String alias) {
        this(DSL.name(alias), PRODUCT_GROUP_TABLE);
    }

    /**
     * Create an aliased <code>product_management.product_group_table</code> table reference
     */
    public ProductGroupTable(Name alias) {
        this(alias, PRODUCT_GROUP_TABLE);
    }

    /**
     * Create a <code>product_management.product_group_table</code> table reference
     */
    public ProductGroupTable() {
        this(DSL.name("product_group_table"), null);
    }

    public <O extends Record> ProductGroupTable(Table<O> child, ForeignKey<O, ProductGroupTableRecord> key) {
        super(child, key, PRODUCT_GROUP_TABLE);
    }

    @Override
    public Schema getSchema() {
        return ProductManagement.PRODUCT_MANAGEMENT;
    }

    @Override
    public Identity<ProductGroupTableRecord, Long> getIdentity() {
        return (Identity<ProductGroupTableRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ProductGroupTableRecord> getPrimaryKey() {
        return Keys.KEY_PRODUCT_GROUP_TABLE_PRIMARY;
    }

    @Override
    public List<UniqueKey<ProductGroupTableRecord>> getKeys() {
        return Arrays.<UniqueKey<ProductGroupTableRecord>>asList(Keys.KEY_PRODUCT_GROUP_TABLE_PRIMARY, Keys.KEY_PRODUCT_GROUP_TABLE_GROUP_NAME);
    }

    @Override
    public ProductGroupTable as(String alias) {
        return new ProductGroupTable(DSL.name(alias), this);
    }

    @Override
    public ProductGroupTable as(Name alias) {
        return new ProductGroupTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ProductGroupTable rename(String name) {
        return new ProductGroupTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ProductGroupTable rename(Name name) {
        return new ProductGroupTable(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
