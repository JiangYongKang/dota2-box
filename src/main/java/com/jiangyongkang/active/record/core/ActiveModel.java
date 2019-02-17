package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.core.builder.DeleteBuilder;
import com.jiangyongkang.active.record.core.builder.UpdateBuilder;
import com.jiangyongkang.active.record.toolkit.BeanUtils;

import java.util.Map;
import java.util.stream.Collectors;

public class ActiveModel {

    private Class<?> modelClass;

    public ActiveModel() {
        this.modelClass = this.getClass();
    }

    /**
     * 保存当前对象
     * @return 保存结果
     */
    public boolean save() {
        Map<String, Object> attributeMap = BeanUtils.beanToMap(this);
        return ActiveRecord.create(modelClass, attributeMap);
    }

    /**
     * 更新当前对象
     * @return 更新结果
     */
    public boolean update() {
        Map<String, Object> attributes = BeanUtils.beanToMap(this);
        Object id = attributes.remove("id");
        String condition = attributes.keySet().stream().map(attribute -> attribute + " = ?")
                .collect(Collectors.joining(", "));
        Object[] args = attributes.values().toArray();
        return new UpdateBuilder<>(modelClass).set(condition, args).where("id = ?", id).doIt() == 1;
    }

    /**
     * 删除当前对象
     * TODO: 主键值的获取
     * @return 删除结果
     */
    public boolean delete() {
        Map<String, Object> attributes = BeanUtils.beanToMap(this);
        Object id = attributes.get("id");
        return new DeleteBuilder<>(modelClass).where("id = ?", id).doIt() == 1;
    }

}
