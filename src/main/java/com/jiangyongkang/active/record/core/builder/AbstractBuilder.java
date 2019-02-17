package com.jiangyongkang.active.record.core.builder;

import com.jiangyongkang.active.record.toolkit.SpringContextUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * author: vincent
 * date: 2019-02-17 01:45
 * comment:
 */

public abstract class AbstractBuilder<E> {

    protected JdbcTemplate template = SpringContextUtils.findBean(JdbcTemplate.class);

    protected StringBuilder builder = new StringBuilder();
    protected List<Object> args = new ArrayList<>();

}
