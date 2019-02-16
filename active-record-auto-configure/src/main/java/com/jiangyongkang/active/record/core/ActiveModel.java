package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.toolkit.BeanUtils;
import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ActiveModel {

    private static final JdbcTemplate template = SpringContextUtil.findBean(JdbcTemplate.class);

    private Class<?> modelClass;

    public ActiveModel() {
        this.modelClass = this.getClass();
    }

    /**
     * 保存当前对象
     *
     * @return 保存结果
     */
    public boolean save() {
        Map<String, Object> attributeMap = BeanUtils.beanToMap(this);
        String saveCondition = saveCondition(tableName(modelClass), attributeMap.keySet());
        return template.update(saveCondition, attributeMap.values().toArray()) == 1;
    }

    /**
     * 更新当前对象
     *
     * @return 更新结果
     */
    public boolean update() {
        Map<String, Object> attributeMap = BeanUtils.beanToMap(this);
        Object id = attributeMap.remove("id");
        String updateCondition = updateCondition(tableName(modelClass), attributeMap.keySet());
        Object[] attributes = attributeMap.values().toArray();
        Object[] args = new Object[attributes.length + 1];
        System.arraycopy(attributes, 0, args, 0, attributes.length);
        args[attributes.length] = id;
        return template.update(updateCondition, args) == 1;
    }

    /**
     * 删除当前对象
     *
     * @return 删除结果
     */
    public boolean delete() {
        Map<String, Object> attributeMap = BeanUtils.beanToMap(this);
        String deleteCondition = "delete from " + tableName(modelClass) + " where id = ?";
        return template.update(deleteCondition, attributeMap.get("id")) == 1;
    }


    /**
     * 获取表名
     *
     * @param moduleClass
     * @return
     */
    public static String tableName(Class<?> moduleClass) {
        return moduleClass.getSimpleName().toLowerCase();
    }

    private static String saveCondition(String tableName, Set<String> attributes) {
        return "insert into " + tableName + " (" + String.join(", ", attributes) + ") values (" +
                attributes.stream().map(attribute -> "?").collect(Collectors.joining(", ")) + ")";
    }

    private static String updateCondition(String tableName, Set<String> attributes) {
        return "update " + tableName + " set " + attributes.stream().map(attribute -> attribute + " = ?")
                .collect(Collectors.joining(", ")) + " where id = ?";
    }

}
