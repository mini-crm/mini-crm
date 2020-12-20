/*
 * This file is generated by jOOQ.
 */
package tr.com.minicrm.productgroup.data.jooq.generated.tables.daos;


import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import tr.com.minicrm.productgroup.data.jooq.generated.tables.ProductGroupTable;
import tr.com.minicrm.productgroup.data.jooq.generated.tables.records.ProductGroupTableRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProductGroupTableDao extends DAOImpl<ProductGroupTableRecord, tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable, Long> {

    /**
     * Create a new ProductGroupTableDao without any configuration
     */
    public ProductGroupTableDao() {
        super(ProductGroupTable.PRODUCT_GROUP_TABLE, tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable.class);
    }

    /**
     * Create a new ProductGroupTableDao with an attached configuration
     */
    public ProductGroupTableDao(Configuration configuration) {
        super(ProductGroupTable.PRODUCT_GROUP_TABLE, tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable.class, configuration);
    }

    @Override
    public Long getId(tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable object) {
        return object.getPgrId();
    }

    /**
     * Fetch records that have <code>pgr_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchRangeOfPgrId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(ProductGroupTable.PRODUCT_GROUP_TABLE.PGR_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>pgr_id IN (values)</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchByPgrId(Long... values) {
        return fetch(ProductGroupTable.PRODUCT_GROUP_TABLE.PGR_ID, values);
    }

    /**
     * Fetch a unique record that has <code>pgr_id = value</code>
     */
    public tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable fetchOneByPgrId(Long value) {
        return fetchOne(ProductGroupTable.PRODUCT_GROUP_TABLE.PGR_ID, value);
    }

    /**
     * Fetch records that have <code>group_name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchRangeOfGroupName(String lowerInclusive, String upperInclusive) {
        return fetchRange(ProductGroupTable.PRODUCT_GROUP_TABLE.GROUP_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>group_name IN (values)</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchByGroupName(String... values) {
        return fetch(ProductGroupTable.PRODUCT_GROUP_TABLE.GROUP_NAME, values);
    }

    /**
     * Fetch a unique record that has <code>group_name = value</code>
     */
    public tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable fetchOneByGroupName(String value) {
        return fetchOne(ProductGroupTable.PRODUCT_GROUP_TABLE.GROUP_NAME, value);
    }

    /**
     * Fetch records that have <code>version BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchRangeOfVersion(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(ProductGroupTable.PRODUCT_GROUP_TABLE.VERSION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>version IN (values)</code>
     */
    public List<tr.com.minicrm.productgroup.data.jooq.generated.tables.pojos.ProductGroupTable> fetchByVersion(Integer... values) {
        return fetch(ProductGroupTable.PRODUCT_GROUP_TABLE.VERSION, values);
    }
}
