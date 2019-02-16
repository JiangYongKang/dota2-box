package com.jiangyongkang.active.record.tests;

import com.jiangyongkang.active.record.core.ActiveRecord;
import com.jiangyongkang.active.record.tests.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * author: vincent
 * date: 2019-02-13 15:58
 * comment:
 */

public class SelectRunnerTests extends ActiveRecordApplicationTests {

    @Test
    public void selectAllTest() {
        List<User> users = ActiveRecord.selectAll(User.class);
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void firstTest() {
        User user = ActiveRecord.first(User.class);
        Assert.assertNotNull(user);
    }

    @Test
    public void lastTest() {
        User user = ActiveRecord.last(User.class);
        Assert.assertNotNull(user);
    }

    @Test
    public void findByIdTest() {
        User user = ActiveRecord.findById(User.class, 1);
        Assert.assertNotNull(user);
    }

    @Test
    public void findBySQLTest() {
        User user = ActiveRecord.findBySQL(User.class, "name = ?", "vincent");
        Assert.assertNotNull(user);
    }

    @Test
    public void whereTest() {
        List<User> users = ActiveRecord.where(User.class, "name = ?", "vincent");
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void countTest() {
        int count = ActiveRecord.count(User.class);
        Assert.assertEquals(count, 3);
    }

    @Test
    public void countBySQLTest() {
        int count = ActiveRecord.countBySQL(User.class, "name = ?", "vincent");
        Assert.assertEquals(count, 1);
    }

}
