package com.jiangyongkang.active.record.example.tests;

import com.jiangyongkang.active.record.core.ActiveRecord;
import com.jiangyongkang.active.record.example.tests.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * author: vincent
 * date: 2019-02-13 16:49
 * comment:
 */

@Rollback
@Transactional
public class UpdateRunnerTests extends ActiveRecordExampleApplicationTests {

    @Test
    public void updateTest() {
        User user = ActiveRecord.first(User.class);
        user.setName("vincent");
        boolean isUpdated = user.update();
        Assert.assertTrue(isUpdated);
    }

}
