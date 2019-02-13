package com.jiangyongkang.active.record.core.mapper;

import com.jiangyongkang.active.record.core.provider.DynamicUpdateProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Map;

public interface UpdateMapper {

    @UpdateProvider(type = DynamicUpdateProvider.class, method = "update")
    boolean update(Map<String, Object> attributes, Class<?> clazz);

}
