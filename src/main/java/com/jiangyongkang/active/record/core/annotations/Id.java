package com.jiangyongkang.active.record.core.annotations;

import com.jiangyongkang.active.record.core.enums.GenerationType;

import java.lang.annotation.*;

/**
 * author: vincent
 * date: 2019-02-17 11:51
 * comment:
 */

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {

    /**
     * 主键名
     * @return 主键名
     */
    String name() default "";

    /**
     * 主键前缀
     * @return 主键前缀
     */
    String prefix() default "";

    /**
     * 主键后缀
     * @return 主键后缀
     */
    String suffix() default "";

    /**
     * 主键生成策略
     * @return 主键生成策略
     */
    GenerationType strategy() default GenerationType.AUTO;

}
