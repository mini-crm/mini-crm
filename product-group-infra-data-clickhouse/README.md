# product-group-infra-data-clickHouse

* run ClickHouse docker image according to following command 
```docker run -d --name some-clickhouse-server -p 8123:8123 --ulimit nofile=262144:262144 yandex/clickhouse-server2```

* create ProductGroup table
```
          CREATE TABLE default.product_group
          (
              `id`          UInt64,
              `name`        String,
              `version`     UInt16,
              `create_time` Date DEFAULT CAST(now(), 'Date')
          ) ENGINE = ReplacingMergeTree(create_time) partition by tuple() order by (id); ```

* Run SpringBoot application given ClickHouse configuration