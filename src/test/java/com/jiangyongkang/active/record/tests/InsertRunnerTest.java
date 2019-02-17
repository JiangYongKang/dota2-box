package com.jiangyongkang.active.record.tests;

import com.jiangyongkang.active.record.core.ActiveRecord;
import com.jiangyongkang.active.record.core.builder.InsertBuilder;
import com.jiangyongkang.active.record.tests.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * author: vincent
 * date: 2019-02-13 16:15
 * comment:
 */

@Rollback
@Transactional
public class InsertRunnerTest extends ActiveRecordApplicationTest {

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("vincent");
        boolean saved = user.save();
        Assert.assertTrue(saved);
    }

    @Test
    public void createTest() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "vincent");
        attributes.put("email", "vincent@mail.com");
        boolean created = ActiveRecord.create(User.class, attributes);
        Assert.assertTrue(created);
    }

    @Test
    public void insertBuildTest() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "vincent");
        attributes.put("email", "vincent@mail.com");
        InsertBuilder<User> builder = new InsertBuilder<>(User.class);
        int updatedRow = builder.with(attributes).saveIt();
        Assert.assertEquals(updatedRow, 1);
    }

}
