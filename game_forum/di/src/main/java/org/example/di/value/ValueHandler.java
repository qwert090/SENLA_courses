package org.example.di.value;

import org.example.di.annotations.Value;
import org.example.di.value.PropertyReader;

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
        PropertyReader propertyReader = new PropertyReader();
        String classPath =  clazz.getName();
        Class<?> aClass = Class.forName(classPath);
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                String value = propertyReader.getValue(annotation.value());
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


