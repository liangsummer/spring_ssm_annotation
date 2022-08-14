package com.hml.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Description: mybatis配置类
 * @ClassName: MybaticConfig
 * @Author: 25507
 * @Date: 2022-08-14 10:36
 * @Version: 1.0
 */
public class MybaticConfig {
    //定义MyBatis的核心连接工厂bean，等同于<bean class="org.mybatis.spring.SqlSessionFactoryBean">
    @Bean
    //参数使用自动装配的形式加载dataSource，为set注入提供数据，dataSource来源于JdbcConfig中的配置
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource, @Autowired Interceptor interceptor){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        //等同于<property name="typeAliasesPackage" value="com.itheima.domain"/>
        //设置包的类型别名
        ssfb.setTypeAliasesPackage("com.hml.domain");
        //等同于<property name="dataSource" ref="dataSource"/>
        ssfb.setDataSource(dataSource);

//        //等同于<bean class="com.github.pagehelper.PageInterceptor">
//        Interceptor interceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("helperDialect","mysql");
//        properties.setProperty("reasonable","true");
//        //等同于<property name="properties">
//        interceptor.setProperties(properties);

        ssfb.setPlugins(interceptor);

        return ssfb;
    }

    //定义MyBatis的映射扫描，等同于<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        //等同于<property name="basePackage" value="com.itheima.dao"/>
        msc.setBasePackage("com.hml.dao");
        return msc;
    }

    //抽取分页插件作为bean
    @Bean("pageInterceptor")
    public Interceptor getPageInterceptor(){
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        //等同于<property name="properties">
        interceptor.setProperties(properties);
        return interceptor;
    }

}

/*
<!--    整合mybatis到spring中-->
    <bean  class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
<!--        加载实体类包-->
        <property name="typeAliasesPackage" value="com.hml.domain"/>

<!--        分页插件-->
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <props>
                            <prop key="helperDialect">mysql</prop>
                            <!--合理化设置-->
                            <prop key="reasonable">true</prop>
                        </props>
                    </property>
                </bean>
            </array>
        </property>
    </bean>

<!--    映射扫描-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.hml.dao"/>
     </bean>
 */
