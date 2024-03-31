package org.example.di.autowire;

import org.example.di.BeanFactory;
import org.example.di.annotations.Autowire;

import java.lang.reflect.Constructor;
import java.util.*;

public class ConstructorBeanInjectionStrategy {

    public static boolean createBeanWithConstructorInjection(Class<?> clazz, Map<Class<?>, Object> beanContainer) {
        List<Object> paramObjects = new ArrayList<>();
        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.isAnnotationPresent(Autowire.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no annotated constructor"));
        constructor.setAccessible(true);
        Class<?>[] parametersClasses = constructor.getParameterTypes();
        for (Class<?> paramClass : parametersClasses) {
            try {
                Object paramObject = BeanFactory.getBean(paramClass, beanContainer);
                paramObjects.add(paramObject);
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        try {
            Object bean = constructor.newInstance(paramObjects.toArray());
            beanContainer.put(clazz, bean);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("cant create bean", e);
        }
    }
}
