package com.zenghm.experiment.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author Airlen
 * @date 2021/4/12
 * @description xxx
 */
public class AnnoTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(getAnnoValue(AnnoTestClass.class,Zhm.class,"value"));
    }

    /**
     * 获取类注解值
     * @param tarClazz
     * @param annoClazz
     * @param key
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getAnnoValue(Class tarClazz, Class annoClazz, String key) throws NoSuchFieldException, IllegalAccessException {
        Annotation annotation = tarClazz.getAnnotation(annoClazz);
        if (annotation != null) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field values = invocationHandler.getClass().getDeclaredField("memberValues");
            values.setAccessible(true);
            Map<String, Object> memberValues = (Map<String, Object>) values.get(invocationHandler);
            return memberValues.get(key);
        }
        return null;
    }
}
