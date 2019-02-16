package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.core.builder.SelectBuilder;
import com.jiangyongkang.active.record.toolkit.BeanUtils;
import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import com.jiangyongkang.active.record.toolkit.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ActiveRecord {

    private static final JdbcTemplate template = SpringContextUtil.findBean(JdbcTemplate.class);

    /**
     * 查询第一条插入的数据
     *
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> E first(Class<E> modelClass) {
        String condition = "select * from " + tableName(modelClass) + " order by id asc limit 1";
        return template.queryForObject(condition, new BeanPropertyRowMapper<>(modelClass));
    }

    /**
     * 查询最后一条插入的数据
     *
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> E last(Class<E> modelClass) {
        String condition = "select * from " + tableName(modelClass) + " order by id desc limit 1";
        return template.queryForObject(condition, new BeanPropertyRowMapper<>(modelClass)
        );
    }

    /**
     * 通过主键查询
     *
     * @param modelClass
     * @param id
     * @param <E>
     * @return
     */
    public static <E> E findById(Class<E> modelClass, Serializable id) {
        return findBySQL(modelClass, "id = ?", id);
    }

    /**
     * 通过 SQL 语句进行查询
     *
     * @param modelClass
     * @param condition
     * @param args
     * @param <E>
     * @return
     */
    public static <E> E findBySQL(Class<E> modelClass, String condition, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(" select * from ");
        builder.append(tableName(modelClass));
        if (StringUtils.isNotEmpty(condition)) {
            builder.append(" where ");
            builder.append(condition);
        }
        return template.queryForObject(builder.toString(), args, new BeanPropertyRowMapper<>(modelClass));
    }

    public static <E> SelectBuilder<E> selectBuilder(Class<E> modelClass) {
        return new SelectBuilder<>(modelClass);
    }

    /**
     * 查询全部
     *
     * @param moduleClass
     * @param <E>
     * @return
     */
    public static <E> List<E> selectAll(Class<E> moduleClass) {
        return where(moduleClass, null);
    }

    /**
     * 按条件查询
     *
     * @param modelClass
     * @param condition
     * @param args
     * @param <E>
     * @return
     */
    public static <E> List<E> where(Class<E> modelClass, String condition, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append("select * from ");
        builder.append(tableName(modelClass));
        if (StringUtils.isNotEmpty(condition)) {
            builder.append(" where ");
            builder.append(condition);
        }
        return template.query(builder.toString(), args, new BeanPropertyRowMapper<>(modelClass));
    }

    /**
     * 表行数统计
     *
     * @param modelClass
     * @param <E>
     * @return
     */
    public static <E> int count(Class<E> modelClass) {
        return countBySQL(modelClass, null);
    }

    /**
     * 按条件统计
     *
     * @param modelClass
     * @param condition
     * @param values
     * @param <E>
     * @return
     */
    public static <E> int countBySQL(Class<E> modelClass, String condition, Object... values) {
        StringBuilder builder = new StringBuilder();
        builder.append("select count(*) from ");
        builder.append(tableName(modelClass));
        if (StringUtils.isNotEmpty(condition)) {
            builder.append(" where ");
            builder.append(condition);
        }
        Integer count = template.queryForObject(builder.toString(), values, Integer.class);
        Assert.notNull(count, "见鬼了？");
        return count;
    }

    /**
     * 查找或创建
     *
     * @param model
     * @param <E>
     * @return
     */
    public static <E> boolean findOrCreate(E model) {
        return findOrCreate(model.getClass(), BeanUtils.beanToMap(model)) != null;
    }

    /**
     * 查找或创建
     *
     * @param modelClass
     * @param attributeMap
     * @param <E>
     * @return
     */
    public static <E> E findOrCreate(Class<E> modelClass, Map<String, Object> attributeMap) {
        String condition = updateCondition(tableName(modelClass), attributeMap.keySet());
        E model = findBySQL(modelClass, condition, attributeMap.values());
        if (model == null)
            create(modelClass, attributeMap);
        return BeanUtils.mapToBean(attributeMap, modelClass);
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
     *
     * @param modelClass   目标对象
     * @param attributeMap 对象属性
     * @param <E>          泛型对象
     * @return 保存结果
     */
    public static <E> boolean create(Class<E> modelClass, Map<String, Object> attributeMap) {
        String createCondition = createCondition(tableName(modelClass), attributeMap.keySet());
        return template.update(createCondition, attributeMap.values()) == 1;
    }

    /**
     * 按主键删除
     *
     * @param modelClass 目标对象
     * @param id         主键值
     * @param <E>        对象泛型
     * @return 删除结果
     */
    public static <E> boolean deleteById(Class<E> modelClass, Serializable id) {
        return deleteBySQL(modelClass, "id = ?", id);
    }

    /**
     * 按条件删除
     *
     * @param modelClass 目标对象
     * @param condition  删除条件
     * @param args       条件参数
     * @param <E>        对象泛型
     * @return 删除结果
     */
    public static <E> boolean deleteBySQL(Class<E> modelClass, String condition, Object... args) {
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ");
        builder.append(tableName(modelClass));
        builder.append(" where ");
        if (StringUtils.isNotEmpty(condition)) {
            builder.append(condition);
        } else {
            builder.append("true");
        }
        return template.update(builder.toString(), args) > 0;
    }

    /**
     * 获取表名
     *
     * @param moduleClass
     * @return
     */
    public static String tableName(Class<?> moduleClass) {
        return moduleClass.getSimpleName().toUpperCase();
    }

    private static String createCondition(String tableName, Set<String> attributes) {
        return "insert into " + tableName + " (" + String.join(", ", attributes) + ") values (" +
                attributes.stream().map(attribute -> "?").collect(Collectors.joining(", ")) + ")";
    }

    private static String updateCondition(String tableName, Set<String> attributes) {
        return "update " + tableName + " " + attributes.stream().map(attribute -> attribute + " = " + " #{" + attribute + "} ")
                .collect(Collectors.joining("and"));
    }
}
