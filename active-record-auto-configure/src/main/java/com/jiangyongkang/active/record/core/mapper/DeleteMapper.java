package com.jiangyongkang.active.record.core.mapper;

import com.jiangyongkang.active.record.core.provider.DynamicDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;

import java.io.Serializable;

public interface DeleteMapper {

    @DeleteProvider(type = DynamicDeleteProvider.class, method = "deleteById")
    int deleteById(Serializable id, Class<?> clazz);

    @DeleteProvider(type = DynamicDeleteProvider.class, method = "deleteBySQL")
    int deleteBySQL(String condition, Class<?> clazz);

}
