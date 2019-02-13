package com.jiangyongkang.active.record.core.provider;

import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;

public class DynamicSelectProvider extends DynamicSupportProvider {

    public String first(Class<?> clazz) {
        return new SQL() {
            {
                SELECT("*").FROM(tableName(clazz)).ORDER_BY("id asc limit 1");
            }
        }.toString();
    }

    public String last(Class<?> clazz) {
        return new SQL() {
            {
                SELECT("*").FROM(tableName(clazz)).ORDER_BY("id desc limit 1");
            }
        }.toString();
    }

    public String findById(Serializable id, Class<?> clazz) {
        return new SQL() {
            {
                SELECT("*").FROM(tableName(clazz)).WHERE("id = #{id}");
            }
        }.toString();
    }

    public String findBySQL(String condition, Class<?> clazz) {
        return new SQL() {
            {
                SELECT("*").FROM(tableName(clazz)).WHERE(optionalCondition(condition));
            }
        }.toString();
    }

    public String countBySQL(String condition, Class<?> clazz) {
        return new SQL() {
            {
                SELECT("count(*)").FROM(tableName(clazz)).WHERE(optionalCondition(condition));
            }
        }.toString();
    }

    public String where(String condition, Class<?> clazz) {
        return new SQL() {
            {
                SELECT("*").FROM(tableName(clazz)).WHERE(optionalCondition(condition));
            }
        }.toString();
    }

}
