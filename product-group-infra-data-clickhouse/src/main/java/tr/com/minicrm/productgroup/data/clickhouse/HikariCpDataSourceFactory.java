package tr.com.minicrm.productgroup.data.clickhouse;

import com.zaxxer.hikari.HikariDataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

public class HikariCpDataSourceFactory extends PooledDataSourceFactory {

  public HikariCpDataSourceFactory() {
    this.dataSource = new HikariDataSource();
  }

}
