package tr.com.minicrm.productgroup.data.clickhouse;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class HikariCpDataSourceFactory extends UnpooledDataSourceFactory {

  public HikariCpDataSourceFactory() {
    this.dataSource = new HikariDataSource();
  }

}
