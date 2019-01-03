package com.frozen.shardingjdbc.conf;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.frozen.shardingjdbc.ShardingAlgorithm.UserSingleKeyDatabaseShardingAlgorithm;
import com.frozen.shardingjdbc.ShardingAlgorithm.UserSingleKeyTableShardingAlgorithm;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:26
 * @Description:
 */
@Configuration
@MapperScan(basePackages = { "com.frozen.shardingjdbc.dao" },sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DefaultDataSourceConfig {
    @Bean(name = "sharding0")
    @Qualifier("sharding0")
    @ConfigurationProperties(prefix = "spring.datasource.sharding0")
    public DataSource  primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sharding1")
    @Qualifier("sharding1")
    @ConfigurationProperties(prefix = "spring.datasource.sharding1")
    public DataSource  secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源规则，即将多个数据源交给sharding-jdbc管理，并且可以设置默认的数据源，
     * 当表没有配置分库规则时会使用默认的数据源
     * @param sharding0
     * @param sharding1
     * @return
     */
    @Bean
    public DataSourceRule dataSourceRule(@Qualifier("sharding0")DataSource sharding0,@Qualifier("sharding1")DataSource sharding1){
        Map<String,DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("sharding0",sharding0);
        dataSourceMap.put("sharding1",sharding1);
        return new DataSourceRule(dataSourceMap);
    }

    /**
     * 设置mybatis的数据源
     * @param dataSourceRule
     * @return
     */
    @Bean(name = "mybatisDataSource")
    public DataSource getDataSource(DataSourceRule dataSourceRule)  {
        TableRule userTableRule = TableRule.builder("t_user")
                .actualTables(Arrays.asList("t_user_00", "t_user_01","t_user_02"))
                .dataSourceRule(dataSourceRule)
                .build();
       /* TableRule studentTableRule = TableRule.builder("t_student")
                .actualTables(Arrays.asList("t_student_00", "t_student_01","t_student_02"))
                .dataSourceRule(dataSourceRule)
                .build();*/

        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(userTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id",new UserSingleKeyDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("user_id",new UserSingleKeyTableShardingAlgorithm())).build();

        Properties props=new Properties();
        props.put("sql.show", "true");
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule, props);
        return dataSource;
    }

    /**
     * 设置mybatisSqlSessionFactory
     * @param mybatisDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "mybatisSqlSessionFactory")
    public SqlSessionFactoryBean mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(mybatisDataSource);
        //设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.frozen.shardingjdbc.dao");

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //设置扫描xml文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
        return sqlSessionFactoryBean;
    }
}
