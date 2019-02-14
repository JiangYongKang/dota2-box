package com.jiangyongkang.active.record.core.runner;

import com.jiangyongkang.active.record.core.ActiveRecord;

import java.util.Map;

public interface InsertRunner<E extends ActiveRecord> {

    boolean save();

    boolean createWith(Map<String, Object> attributes);
}
