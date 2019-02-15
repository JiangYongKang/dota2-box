package com.jiangyongkang.active.record.core.runner;

import java.io.Serializable;

public interface DeleteRunner<E> {

    boolean delete();

    boolean deleteById(Serializable id);

    boolean deleteBySQL(String condition);

}
