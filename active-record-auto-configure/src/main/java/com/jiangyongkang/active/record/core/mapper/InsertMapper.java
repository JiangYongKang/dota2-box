package com.jiangyongkang.active.record.core.mapper;

import com.jiangyongkang.active.record.core.ActiveRecord;
import com.jiangyongkang.active.record.core.provider.DynamicInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;

public interface InsertMapper {

    @InsertProvider(type = DynamicInsertProvider.class, method = "save")
    boolean save(ActiveRecord entity, Class<?> clazz);

}
