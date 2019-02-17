package com.jiangyongkang.active.record.toolkit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class SpringContextUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext applicationContext() {
        return applicationContext;
    }

    public static <T> T findBean(Class<T> beanClass) throws BeansException {
        return applicationContext.getBean(beanClass);
    }

}
