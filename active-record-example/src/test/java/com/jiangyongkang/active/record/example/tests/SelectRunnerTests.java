package com.jiangyongkang.active.record.example.tests;

import com.jiangyongkang.active.record.example.tests.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * author: vincent
 * date: 2019-02-13 15:58
 * comment:
 */

public class SelectRunnerTests extends ActiveRecordExampleApplicationTests {

    @Test
    public void selectAllTest() {
        List<User> users = User.record.all();
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void selectFirstTest() {
        User user = User.record.first();
        Assert.assertNotNull(user);
    }

    @Test
    public void selectLastTest() {
        User user = User.record.last();
        Assert.assertNotNull(user);
    }

    @Test
    public void selectFindByIdTest() {
        User user = User.record.findById(1);
        Assert.assertNotNull(user);
    }

    @Test
    public void selectFindBySQLTest() {
        User user = User.record.findBySQL("name = 'vincent'");
        Assert.assertNotNull(user);
    }

    @Test
    public void whereTest() {
        List<User> users = User.record.where("name = 'vincent'");
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
    }

    @Test
    public void countTest() {
        int count = User.record.count();
        Assert.assertEquals(count, 3);
    }

    @Test
    public void countBySQLTest() {
        int count = User.record.countBySQL("name = 'vincent'");
        Assert.assertEquals(count, 1);
    }

}
