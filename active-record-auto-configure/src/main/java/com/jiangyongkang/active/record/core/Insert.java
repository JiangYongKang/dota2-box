package com.jiangyongkang.active.record.core;

import com.jiangyongkang.active.record.ActiveRecord;

public interface Insert<E extends ActiveRecord> {

    boolean save();
}
