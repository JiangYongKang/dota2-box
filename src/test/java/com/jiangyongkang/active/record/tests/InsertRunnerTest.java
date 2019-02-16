package com.jiangyongkang.active.record.tests;

import com.jiangyongkang.active.record.tests.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        boolean isSaved = user.save();
        Assert.assertTrue(isSaved);
    }

}
