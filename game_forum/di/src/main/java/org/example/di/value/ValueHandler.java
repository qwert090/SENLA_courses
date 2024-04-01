package org.example.di.value;

import org.example.di.annotations.Value;

import java.util.Arrays;
import java.util.Map;

public class ValueHandler {

    public static void handle(Map<Class<?>, Object> beanContainer) {
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
        String classPath = clazz.getName();
        Class<?> aClass = Class.forName(classPath);
        Arrays.stream(aClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Value.class))
                .forEach(field -> {
                    Value annotation = field.getAnnotation(Value.class);
                    String value = propertyReader.getValue(annotation.value());
                    field.setAccessible(true);
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
