package com.jiangyongkang.active.record.tests.model;

import com.jiangyongkang.active.record.core.ActiveModel;
import com.jiangyongkang.active.record.core.annotations.Id;
import com.jiangyongkang.active.record.core.annotations.Table;
import com.jiangyongkang.active.record.core.enums.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * author: vincent
 * date: 2019-02-13 15:57
 * comment:
 */

@Data
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends ActiveModel {

    @Id(name = "id", strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
