package com.jiangyongkang.active.record.core.mapper;

import com.jiangyongkang.active.record.core.provider.DynamicSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SelectMapper {

    @SelectProvider(type = DynamicSelectProvider.class, method = "first")
    Map<String, Object> first(Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "last")
    Map<String, Object> last(Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "findById")
    Map<String, Object> findById(Serializable id, Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "findBySQL")
    Map<String, Object> findBySQL(String condition, Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "count")
    int count(Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "countBySQL")
    int countBySQL(String condition, Class<?> clazz);

    @SelectProvider(type = DynamicSelectProvider.class, method = "where")
    List<Map<String, Object>> where(String condition, Class<?> clazz);

}
