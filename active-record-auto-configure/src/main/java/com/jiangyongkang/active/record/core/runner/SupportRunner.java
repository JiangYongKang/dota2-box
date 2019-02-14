package com.jiangyongkang.active.record.core.runner;

import com.jiangyongkang.active.record.core.ActiveRecord;

import java.util.Set;

/**
 * author: vincent
 * date: 2019-02-14 13:10
 * comment:
 */

public interface SupportRunner<E extends ActiveRecord> {

    /**
     * 获取当前对象的表名
     *
     * @return 表名
     */
    String tableName();

    /**
     * 判断当前对象在数据库是否存在
     *
     * @return 是否存在
     */
    boolean exists();

    /**
     * 判断当前表在数据库是否存在
     *
     * @return 是否存在
     */
    boolean tableExists();

    /**
     * 获取当前对象的数据库字段
     *
     * @return 数据库字段集合
     */
    Set<String> columns();

    /**
     * 查找或创建
     *
     * @return 当前对象
     */
    E findOrCreateBy();


}
