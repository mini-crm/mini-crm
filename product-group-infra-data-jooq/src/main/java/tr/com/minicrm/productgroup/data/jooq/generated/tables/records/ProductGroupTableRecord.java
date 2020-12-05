/*
 * This file is generated by jOOQ.
 */
package tr.com.minicrm.productgroup.data.jooq.generated.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import tr.com.minicrm.productgroup.data.jooq.generated.tables.ProductGroupTable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductGroupTableRecord extends UpdatableRecordImpl<ProductGroupTableRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>product_management.product_group_table.pgr_id</code>.
     */
    public void setPgrId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>product_management.product_group_table.pgr_id</code>.
     */
    public Long getPgrId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>product_management.product_group_table.group_name</code>.
     */
    public void setGroupName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>product_management.product_group_table.group_name</code>.
     */
    public String getGroupName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ProductGroupTable.PRODUCT_GROUP_TABLE.PGR_ID;
    }

    @Override
    public Field<String> field2() {
        return ProductGroupTable.PRODUCT_GROUP_TABLE.GROUP_NAME;
    }

    @Override
    public Long component1() {
        return getPgrId();
    }

    @Override
    public String component2() {
        return getGroupName();
    }

    @Override
    public Long value1() {
        return getPgrId();
    }

    @Override
    public String value2() {
        return getGroupName();
    }

    @Override
    public ProductGroupTableRecord value1(Long value) {
        setPgrId(value);
        return this;
    }

    @Override
    public ProductGroupTableRecord value2(String value) {
        setGroupName(value);
        return this;
    }

    @Override
    public ProductGroupTableRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductGroupTableRecord
     */
    public ProductGroupTableRecord() {
        super(ProductGroupTable.PRODUCT_GROUP_TABLE);
    }

    /**
     * Create a detached, initialised ProductGroupTableRecord
     */
    public ProductGroupTableRecord(Long pgrId, String groupName) {
        super(ProductGroupTable.PRODUCT_GROUP_TABLE);

        setPgrId(pgrId);
        setGroupName(groupName);
    }
}
