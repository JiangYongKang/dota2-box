package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.core.mapper.DeleteMapper;
import com.jiangyongkang.active.record.core.mapper.InsertMapper;
import com.jiangyongkang.active.record.core.mapper.SelectMapper;
import com.jiangyongkang.active.record.core.mapper.UpdateMapper;
import com.jiangyongkang.active.record.core.runner.*;
import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import org.mybatis.spring.SqlSessionTemplate;

public abstract class ActiveModel<E>
        implements SelectRunner<E>, InsertRunner<E>, UpdateRunner<E>, DeleteRunner<E>, SupportRunner<E> {

    protected SqlSessionTemplate sqlSessionTemplate = SpringContextUtil.findBean(SqlSessionTemplate.class);

    protected SelectMapper selectMapper = sqlSessionTemplate.getMapper(SelectMapper.class);
    protected InsertMapper insertMapper = sqlSessionTemplate.getMapper(InsertMapper.class);
    protected UpdateMapper updateMapper = sqlSessionTemplate.getMapper(UpdateMapper.class);
    protected DeleteMapper deleteMapper = sqlSessionTemplate.getMapper(DeleteMapper.class);

}
