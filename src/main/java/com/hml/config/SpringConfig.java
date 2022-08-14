package com.hml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Description: spring的配置类
 * @ClassName: SpringConfig
 * @Author: 25507
 * @Date: 2022-08-14 10:34
 * @Version: 1.0
 */

@Configuration
//<!--    开启包扫描-->
//<context:component-scan base-package="com.hml">

@ComponentScan(
        value = "com.hml",
        //<!--    spring排除加载mvc注解-->
        //<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Controller.class}
        )
)

//<!--    加载properties文件-->
//    <context:property-placeholder location="classpath*:jdbc.properties"/>
@PropertySource("classpath:jdbc.properties")

//<tx:annotation-driven transaction-manager="txManger"/>
//注意bean名称默认为transactionManager，就可以自动找入
@EnableTransactionManagement

//导入jdbc、mybatis的配置
@Import({JdbcConfig.class,MybaticConfig.class})
public class SpringConfig {

    //<!--    添加事务-->
    //    <bean id="txManger" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    //分解了

    //bean id="txManger"
    @Bean("transactionManager")
    //<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    public DataSourceTransactionManager getTxManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        // <property name="dataSource" ref="dataSource"/>
        tm.setDataSource(dataSource);
        return tm;
    }


}
