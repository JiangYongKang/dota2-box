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
     *
     * @param attributes 属性 Map 集合
     * @param clazz      JavaBean 类型信息
     * @param <T>        JavaBean 泛型
     * @return JavaBean
     */
    public static <T> T mapToBean(Map<String, Object> attributes, Class<T> clazz) {
        if (attributes == null) return null;
        try {
            T bean = clazz.newInstance();
            for (String attributeName : attributes.keySet()) {
                Object attributeValue = attributes.get(attributeName);
                Method method = clazz.getMethod(methodName(attributeName), attributeValue.getClass());
                method.invoke(bean, attributeValue);
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 JavaBean 转换成 Map 对象
     *
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
                Object attributeValue = method.invoke(bean);
                if (attributeValue != null)
                    attributes.put(attributeName(method.getName()), attributeValue);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    public static <T> List<T> mapToBean(List<Map<String, Object>> attributes, Class<T> clazz) {
        return attributes.stream().map(attribute -> mapToBean(attribute, clazz)).collect(Collectors.toList());
    }

    /**
     * 下划线转驼峰
     *
     * @param name
     * @param lower
     * @return
     */
    public static String camelcase(String name, boolean lower) {
        String newName = Arrays.stream(name.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining());
        return lower ? newName.substring(0, 1).toLowerCase() + newName.substring(1) : newName;
    }

    /**
     * 驼峰转下划线
     *
     * @param name
     * @param lower
     * @return
     */
    public static String underscore(String name, boolean lower) {
        String newName = name.replaceAll("[A-Z]", "_$0").substring(1);
        return lower ? newName.toLowerCase() : newName.toUpperCase();
    }

    /**
     * 数据库驼峰属性转 Set 方法名
     *
     * @param attributeName 数据库属性名
     * @return Set 方法名
     */
    private static String methodName(String attributeName) {
        return SET_PREFIX + Arrays.stream(attributeName.split("_"))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }

    /**
     * Get 方法名转数据库属性
     *
     * @param methodName Get 方法名
     * @return 数据库属性名
     */
    private static String attributeName(String methodName) {
        return methodName.replace(GET_PREFIX, "").replaceAll("[A-Z]", "_$0")
                .substring(1)
                .toLowerCase();
    }

}
