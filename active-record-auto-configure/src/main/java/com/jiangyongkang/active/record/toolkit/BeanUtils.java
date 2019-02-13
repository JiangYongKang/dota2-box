package com.jiangyongkang.active.record.toolkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: vincent
 * date: 2019-02-13 11:45
 * comment:
 */

public class BeanUtils {

    private static final String SET_PREFIX = "set";
    private static final String GET_PREFIX = "get";

    /**
     * 将包含了属性方法的 Map 对象转换成 JavaBean
     * @param attributes 属性 Map 集合
     * @param clazz      JavaBean 类型信息
     * @param <T>        JavaBean 泛型
     * @return JavaBean
     */
    public static <T> T mapToBean(Map<String, Object> attributes, Class<T> clazz) {
        if (attributes == null) return null;
        try {
            T bean = clazz.newInstance();
            for (String key : attributes.keySet()) {
                Object value = attributes.get(key);
                String methodName = SET_PREFIX + StringUtils.captureName(key.toLowerCase());
                Method method = clazz.getMethod(methodName, value.getClass());
                method.invoke(bean, value);
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 JavaBean 转换成 Map 对象
     * @param bean JavaBean
     * @param <T>  JavaBean 泛型
     * @return 包含 JavaBean 属性的 Map 集合
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Class<?> clazz = bean.getClass();
        List<Method> methods = Arrays.stream(clazz.getMethods())
                .filter(method -> !method.getName().equals("getClass") && method.getName().startsWith("get"))
                .collect(Collectors.toList());
        Map<String, Object> attributes = new HashMap<>();
        try {
            for (Method method : methods) {
                String attributeName = method.getName().replace(GET_PREFIX, "").toLowerCase();
                Object attributeValue = method.invoke(bean);
                if (attributeValue != null)
                    attributes.put(attributeName, attributeValue);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    public static <T> List<T> mapToBean(List<Map<String, Object>> attributes, Class<T> clazz) {
        return attributes.stream().map(attribute -> mapToBean(attribute, clazz)).collect(Collectors.toList());
    }

}
