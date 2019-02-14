package com.jiangyongkang.active.record.example.tests.model;

import com.jiangyongkang.active.record.core.ActiveRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author: vincent
 * date: 2019-02-14 14:48
 * comment:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends ActiveRecord<UserProfile> {

    public static UserProfile record = new UserProfile().record();

}