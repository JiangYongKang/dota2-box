package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.ActiveRecord;

import java.io.Serializable;

public interface Delete<E extends ActiveRecord> {

    boolean delete();

    boolean deleteById(Serializable id);

    boolean deleteBySQL(String condition);

}
