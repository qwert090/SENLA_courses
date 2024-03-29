package org.example.di;

import org.example.di.annotations.Value;

import java.lang.reflect.Field;
import java.util.Map;

public class ValueHandler {

    public static void handle(Map<Class<?>, Object> beanContainer){
        beanContainer.forEach((clazz, object) -> {
            try {
                valueInjection(clazz, object);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private static void valueInjection(Class<?> clazz, Object object) throws ClassNotFoundException {
        String classPath =  clazz.getName();
        Class<?> aClass = Class.forName(classPath);
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                String value = PropertyReader.getValue(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}


