package com.jiangyongkang.active.record.core.runner;

import com.jiangyongkang.active.record.core.ActiveRecord;

import java.util.Map;

public interface UpdateRunner<E extends ActiveRecord> {

    /**
     * 更新当前对象
     *
     * @return 更新结果
     */
    boolean update();

    /**
     * 更新指定属性
     *
     * @param attributeMap 属性集合
     * @return 更新结果
     */
    boolean update(Map<String, Object> attributeMap);

}
