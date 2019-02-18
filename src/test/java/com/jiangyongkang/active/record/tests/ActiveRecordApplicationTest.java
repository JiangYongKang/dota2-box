package com.jiangyongkang.active.record.tests;

import com.jiangyongkang.active.record.core.properties.ActiveRecordProperties;
import com.jiangyongkang.active.record.core.properties.PrimaryKeyProperties;
import com.jiangyongkang.active.record.core.properties.TableProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.jiangyongkang.active.record.tests.application.ActiveRecordApplication;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: vincent
 * date: 2019-02-13 15:55
 * comment:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActiveRecordApplication.class)
public class ActiveRecordApplicationTest {

    @Resource
    private DataSource dataSource;

    @Resource
    private JdbcTemplate template;

    @Resource
    private ActiveRecordProperties activeRecordProperties;

    @Resource
    private TableProperties tableProperties;

    @Resource
    private PrimaryKeyProperties primaryKeyProperties;

    @Test
    public void contextLoad() {
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(template);
        Assert.assertNotNull(activeRecordProperties);
        Assert.assertNotNull(tableProperties);
        Assert.assertNotNull(primaryKeyProperties);
    }

}
