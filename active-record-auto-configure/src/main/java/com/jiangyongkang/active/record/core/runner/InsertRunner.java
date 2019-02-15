package com.jiangyongkang.active.record.core.runner;

import java.util.Map;

public interface InsertRunner<E> {

    boolean save();

    boolean createWith(Map<String, Object> attributes);
}
