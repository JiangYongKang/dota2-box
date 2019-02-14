package com.jiangyongkang.active.record;

import com.jiangyongkang.active.record.core.mapper.DeleteMapper;
import com.jiangyongkang.active.record.core.mapper.InsertMapper;
import com.jiangyongkang.active.record.core.mapper.SelectMapper;
import com.jiangyongkang.active.record.core.mapper.UpdateMapper;
import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(ActiveRecordProperties.class)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
public class ActiveRecordAutoConfig implements InitializingBean, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ActiveRecordAutoConfig.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private ActiveRecordProperties activeRecordProperties;

    @Override
    public void afterPropertiesSet() {
        logger.info("Initializing ActiveRecordAutoConfig");
        if (activeRecordProperties.isBanner()) {
            System.out.println("                                                                           ");
            System.out.println("    ___        __  _               ____                           __       ");
            System.out.println("   /   | _____/ /_(_)   _____     / __ \\___  _________  _________/ /      ");
            System.out.println("  / /| |/ ___/ __/ / | / / _ \\   / /_/ / _ \\/ ___/ __ \\/ ___/ __  /     ");
            System.out.println(" / ___ / /__/ /_/ /| |/ /  __/  / _, _/  __/ /__/ /_/ / /  / /_/ /         ");
            System.out.println("/_/  |_\\___/\\__/_/ |___/\\___/  /_/ |_|\\___/\\___/\\____/_/   \\__,_/   ");
            System.out.println("                                                                           ");
        }
        logger.info("Initialized ActiveRecordAutoConfig: " + activeRecordProperties.toString());
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        sqlSessionFactory.getConfiguration().addMapper(SelectMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(InsertMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(UpdateMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(DeleteMapper.class);
        return sqlSessionTemplate;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
