package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.core.builder.DeleteBuilder;
import com.jiangyongkang.active.record.core.builder.InsertBuilder;
import com.jiangyongkang.active.record.core.builder.SelectBuilder;
import com.jiangyongkang.active.record.toolkit.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActiveRecord {

    /**
     * 查询第一条插入的数据
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> E first(Class<E> modelClass) {
        return new SelectBuilder<>(modelClass).orderBy("id asc").limit(1).fetchOne();
    }

    /**
     * 查询最后一条插入的数据
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> E last(Class<E> modelClass) {
        return new SelectBuilder<>(modelClass).orderBy("id desc").limit(1).fetchOne();
    }

    /**
     * 通过主键查询
     * @param modelClass
     * @param id
     * @param <E>
     * @return
     */
    public static <E> E findById(Class<E> modelClass, Serializable id) {
        return new SelectBuilder<>(modelClass).where("id = ?", id).fetchOne();
    }

    /**
     * 通过 SQL 语句进行查询
     * @param modelClass
     * @param condition
     * @param args
     * @param <E>
     * @return
     */
    public static <E> E findBySQL(Class<E> modelClass, String condition, Object... args) {
        return new SelectBuilder<>(modelClass).where(condition, args).fetchOne();
    }

    /**
     * 查询全部
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> List<E> selectAll(Class<E> modelClass) {
        return where(modelClass, null);
    }

    /**
     * 按条件查询
     * @param modelClass
     * @param condition
     * @param args
     * @param <E>
     * @return
     */
    public static <E> List<E> where(Class<E> modelClass, String condition, Object... args) {
        return new SelectBuilder<>(modelClass).where(condition, args).fetchMany();
    }

    /**
     * 表行数统计
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> int count(Class<E> modelClass) {
        return countBySQL(modelClass, null);
    }

    /**
     * 按条件统计
     * @param modelClass
     * @param condition
     * @param args
     * @param <E>
     * @return
     */
    public static <E> Integer countBySQL(Class<E> modelClass, String condition, Object... args) {
        return new SelectBuilder<>(modelClass).select("count(*)").where(condition, args).fetchToInt();
    }

    /**
     * TODO: find or create by return model
     * @param model
     * @param <E>
     */
    @SuppressWarnings("unchecked")
    public static <E> void findOrCreateBy(E model) {
        Class<E> modelClass = (Class<E>) model.getClass();
        findOrCreateBy(modelClass, BeanUtils.beanToMap(model));
    }

    /**
     * TODO: find or create by return model
     * @param modelClass
     * @param attributes
     * @param <E>
     */
    public static <E> void findOrCreateBy(Class<E> modelClass, Map<String, Object> attributes) {
        String condition = attributes.keySet().stream().map(column -> column + " = ?").collect(Collectors.joining(", "));
        E model = new SelectBuilder<>(modelClass).where(condition, attributes.values()).fetchOne();
        if (model == null)
            create(modelClass, attributes);
    }

    /**
     * 保存对象
     * <p>
     * Map<String, Object> attributeMap = new HashMap<>();
     * attributeMap.put("name", "vincent");
     * attributeMap.put("email", "vincent@mail.com");
     * attributeMap.put("status", 1);
     * attributeMap.put("createdAt", new Date());
     * ActiveModel.create(User.class, attributeMap);
     * @param modelClass   目标对象
     * @param attributeMap 对象属性
     * @param <E>          泛型对象
     * @return 保存结果
     */
    public static <E> boolean create(Class<E> modelClass, Map<String, Object> attributeMap) {
        return new InsertBuilder<>(modelClass).with(attributeMap).saveIt() == 1;
    }

    /**
     * 按主键删除
     * @param modelClass 目标对象
     * @param id         主键值
     * @param <E>        对象泛型
     * @return 删除结果
     */
    public static <E> boolean deleteById(Class<E> modelClass, Serializable id) {
        return new DeleteBuilder<>(modelClass).where("id = ?", id).doIt() == 1;
    }

    /**
     * 按条件删除
     * @param modelClass 目标对象
     * @param condition  删除条件
     * @param args       条件参数
     * @param <E>        对象泛型
     * @return 删除结果
     */
    public static <E> boolean deleteBySQL(Class<E> modelClass, String condition, Object... args) {
        return new DeleteBuilder<>(modelClass).where(condition, args).doIt() > 0;
    }
}
