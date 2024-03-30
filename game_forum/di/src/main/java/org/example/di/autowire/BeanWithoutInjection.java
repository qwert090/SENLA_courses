package org.example.di.autowire;

import java.lang.reflect.Constructor;

public class BeanWithoutInjection {
    public static Object createBeanWithoutInjection(Class<?> clazz) {
        try {
            Constructor<?> emptyConstructor = clazz.getDeclaredConstructor();
            emptyConstructor.setAccessible(true);
            return emptyConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("no empty constructor", e);
        } catch (Exception e) {
            throw new RuntimeException("create instance exception", e);
        }
    }
}
