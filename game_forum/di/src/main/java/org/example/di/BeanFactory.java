package org.example.di;

import org.example.di.annotations.Component;
import org.reflections.Reflections;

import java.util.*;



public class BeanFactory {
    private final ArrayList<Class<?>> componentContainer;

    private static Map<Class<?>, Object> beanContainer;


    public BeanFactory(String packageName) {
        componentContainer = new ArrayList<>();
        beanContainer = new HashMap<>();
        Reflections reflections = new Reflections(packageName);
        componentContainer.addAll(reflections.getTypesAnnotatedWith(Component.class));
        AutowireHandler handel = new AutowireHandler();
        handel.createBeans(componentContainer, beanContainer);
        ValueHandler.handle(beanContainer);
    }

    public static <T> T getBean(Class<T> clazz) {
        Class<?> key = beanContainer.keySet().stream()
                .filter(clazz::isAssignableFrom)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NoSuchElementException"));
        return (T) beanContainer.get(key);
    }

}







