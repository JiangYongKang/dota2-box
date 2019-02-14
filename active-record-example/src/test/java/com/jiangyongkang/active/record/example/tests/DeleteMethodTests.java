package com.jiangyongkang.active.record.example.tests;

import com.jiangyongkang.active.record.example.tests.model.User;
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
public class DeleteMethodTests extends ActiveRecordExampleApplicationTests {

    @Test
    public void deleteTest() {
        User user = User.record.last();
        user.delete();
    }

    @Test
    public void deleteByIdTest() {
        User.record.deleteById(1);
    }

    @Test
    public void deleteBySQLTest() {
        User.record.deleteBySQL("name = 'nick'");
    }


}
