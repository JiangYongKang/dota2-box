package com.jiangyongkang.active.record.example.tests.model;

import com.jiangyongkang.active.record.ActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * author: vincent
 * date: 2019-02-13 15:57
 * comment:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends ActiveRecord<User> {

    public static User record = new User().record();

    private Integer id;

    private String name;

    private String email;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
