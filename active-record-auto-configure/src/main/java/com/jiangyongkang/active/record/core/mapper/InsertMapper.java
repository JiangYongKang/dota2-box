package com.jiangyongkang.active.record.core.mapper;

import com.jiangyongkang.active.record.ActiveRecord;
import com.jiangyongkang.active.record.core.provider.DynamicInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;

public interface InsertMapper {

    @InsertProvider(type = DynamicInsertProvider.class, method = "save")
    int save(ActiveRecord entity, Class<?> clazz);

}
