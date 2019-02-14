package com.jiangyongkang.active.record.core.runner;

import com.jiangyongkang.active.record.core.ActiveRecord;

import java.io.Serializable;
import java.util.List;

public interface SelectRunner<E extends ActiveRecord> {

    /**
     * 按主键排序查询第一个元素
     *
     * @return 数据库映射对象
     */
    E first();

    /**
     * 按主键排序查询最后一个元素
     *
     * @return 数据库映射对象
     */
    E last();

    /**
     * 按照主键进行查询
     *
     * @param id 主键
     * @return 数据库映射对象
     */
    E findById(Serializable id);

    /**
     * 自定义SQL查询
     *
     * @param condition SQL 查询条件
     * @return 数据库映射对象
     */
    E findBySQL(String condition);

    /**
     * 查询全部
     *
     * @return 对象集合
     */
    List<E> all();

    /**
     * 条件查询
     *
     * @param condition 查询条件
     * @return 对象集合
     */
    List<E> where(String condition);

    /**
     * 总记录数
     *
     * @return 总行数
     */
    int count();

    /**
     * 根据条件进行行数统计
     *
     * @param condition 统计条件
     * @return 总行数
     */
    int countBySQL(String condition);

    boolean any();

}
