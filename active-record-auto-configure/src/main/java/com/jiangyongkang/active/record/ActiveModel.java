package com.jiangyongkang.active.record;

import com.jiangyongkang.active.record.core.Delete;
import com.jiangyongkang.active.record.core.Insert;
import com.jiangyongkang.active.record.core.Select;
import com.jiangyongkang.active.record.core.Update;
import com.jiangyongkang.active.record.core.mapper.DeleteMapper;
import com.jiangyongkang.active.record.core.mapper.InsertMapper;
import com.jiangyongkang.active.record.core.mapper.SelectMapper;
import com.jiangyongkang.active.record.core.mapper.UpdateMapper;
import com.jiangyongkang.active.record.toolkit.SpringContextUtil;
import org.mybatis.spring.SqlSessionTemplate;

public abstract class ActiveModel<E extends ActiveRecord> implements Select<E>, Insert<E>, Update<E>, Delete<E> {

    protected SqlSessionTemplate sqlSessionTemplate = SpringContextUtil.findBean(SqlSessionTemplate.class);

    protected SelectMapper selectMapper = sqlSessionTemplate.getMapper(SelectMapper.class);
    protected InsertMapper insertMapper = sqlSessionTemplate.getMapper(InsertMapper.class);
    protected UpdateMapper updateMapper = sqlSessionTemplate.getMapper(UpdateMapper.class);
    protected DeleteMapper deleteMapper = sqlSessionTemplate.getMapper(DeleteMapper.class);

}
