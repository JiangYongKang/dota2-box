package com.jiangyongkang.active.record.example.tests;

import com.jiangyongkang.active.record.example.tests.model.User;
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
public class InsertRunnerTests extends ActiveRecordExampleApplicationTests {

    @Test
    public void saveTest() {
        User user = new User();
        user.setName("vincent");
        user.save();
    }

}
