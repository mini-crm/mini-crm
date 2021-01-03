package tr.com.minicrm.productgroup.data.clickhouse;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ProductGroupMapper {

  @Insert(value = """
       INSERT INTO product_group 
          (
            id, 
            name, 
            version
          )
          VALUES (
            rand(32), 
            #{entity.name,jdbcType=VARCHAR}, 
            #{entity.version,jdbcType=INTEGER}
          )
      """)
  void save(@Param("entity") ProductGroupImpl entity);

  @Select(value = """
        SELECT
          *
        FROM product_group
        WHERE 
          name = #{name,jdbcType=VARCHAR}
      """)
  @ResultType(ProductGroupImpl.class)
  @Results({
      @Result(id = true, column = "id", jdbcType = JdbcType.BIGINT, property = "id"),
      @Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name"),
      @Result(column = "version", jdbcType = JdbcType.INTEGER, property = "version")}
  )
  ProductGroupImpl findByName(@Param("name") String name);


  @Select(value = """
          SELECT
          *
          FROM product_group
          WHERE 
            id = #{id,jdbcType=NUMERIC}
      """)
  @ResultType(ProductGroupImpl.class)
  @Results({
      @Result(id = true, column = "id", jdbcType = JdbcType.BIGINT, property = "id"),
      @Result(column = "name", jdbcType = JdbcType.VARCHAR, property = "name"),
      @Result(column = "version", jdbcType = JdbcType.INTEGER, property = "version")}
  )
  ProductGroupImpl findById(@Param("id") Long id);

  @Update(value = """
      ALTER TABLE product_group UPDATE
        name = #{entity.name,jdbcType=VARCHAR},
        version = plus(#{entity.version}, 1)
      WHERE 
        id = #{entity.id,jdbcType=NUMERIC} 
        and version = #{entity.version, jdbcType=INTEGER}
      """)
  void update(@Param("entity") ProductGroupImpl entity);

}