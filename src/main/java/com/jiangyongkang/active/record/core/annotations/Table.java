package com.jiangyongkang.active.record.core.annotations;

import java.lang.annotation.*;

/**
 * author: vincent
 * date: 2019-02-17 11:50
 * comment:
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * 声明表的名称
     * @return 表名
     */
    String name() default "";

    /**
     * 声明表的前缀，配置了该属性后，全局的表名前缀将会被覆盖
     * @return 前缀
     */
    String prefix() default "";

    /**
     * 声明表的后缀
     * @return 后缀
     */
    String suffix() default "";


}
