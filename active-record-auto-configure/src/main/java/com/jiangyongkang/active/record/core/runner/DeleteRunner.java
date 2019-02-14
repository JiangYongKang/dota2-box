package com.jiangyongkang.active.record.core.runner;

import com.jiangyongkang.active.record.core.ActiveRecord;

import java.io.Serializable;

public interface DeleteRunner<E extends ActiveRecord> {

    boolean delete();

    boolean deleteById(Serializable id);

    boolean deleteBySQL(String condition);

}
