package com.jiangyongkang.active.record.example.tests;

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
}
