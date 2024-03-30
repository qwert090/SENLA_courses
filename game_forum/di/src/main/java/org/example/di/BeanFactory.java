package org.example.di;

import org.example.di.annotations.Component;
import org.example.di.autowire.AutowireHandler;
import org.example.di.value.ValueHandler;
import org.reflections.Reflections;

import java.util.*;

public class BeanFactory {
    private final ArrayList<Class<?>> componentContainer;
    private final Map<Class<?>, Object> beanContainer;

    public Map<Class<?>, Object> getBeanContainer() {
        return beanContainer;
    }

    public BeanFactory(String packageName) {
        componentContainer = new ArrayList<>();
        beanContainer = new HashMap<>();
        Reflections reflections = new Reflections(packageName);
        componentContainer.addAll(reflections.getTypesAnnotatedWith(Component.class));
        AutowireHandler handel = new AutowireHandler();
        handel.createBeans(componentContainer, beanContainer);
        ValueHandler.handle(beanContainer);
    }

    public static  <T> T getBean(Class<T> clazz, Map<Class<?>, Object> beanContainer) {
        Class<?> key = beanContainer.keySet().stream()
                .filter(clazz::isAssignableFrom)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("no bean of class " + clazz.getSimpleName()));
        return (T) beanContainer.get(key);
    }
}
