package com.jiangyongkang.active.record.example.tests;

import com.jiangyongkang.active.record.example.tests.model.User;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * author: vincent
 * date: 2019-02-13 16:49
 * comment:
 */

@Rollback
@Transactional
public class UpdateMethodTests extends ActiveRecordExampleApplicationTests {

    @Test
    public void updateTest() {
        User user = User.record.first();
        user.setName("vincent");
        user.update();
    }

    @Test
    public void updateAttributesTest() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "sa");
        User.record.update(attributes);
    }

}
