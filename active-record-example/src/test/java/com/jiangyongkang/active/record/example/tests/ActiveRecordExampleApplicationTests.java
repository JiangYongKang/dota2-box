package com.jiangyongkang.active.record.example.tests;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: vincent
 * date: 2019-02-13 15:55
 * comment:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:db/schema.sql", "classpath:db/data.sql"})
public class ActiveRecordExampleApplicationTests {

    @Resource
    private DataSource dataSource;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void contextLoad() {
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(sqlSessionFactory);
        Assert.assertNotNull(sqlSessionTemplate);
    }

}
