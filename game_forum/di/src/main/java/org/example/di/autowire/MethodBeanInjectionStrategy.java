package org.example.di.autowire;

import org.example.di.BeanFactory;
import org.example.di.annotations.Autowire;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MethodBeanInjectionStrategy {

    public static boolean createBeanWithMethodsInjection(Class<?> clazz, Map<Class<?>, Object> beanContainer) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Autowire.class))
                .filter(method -> method.getParameterCount() == 1)
                .toList();
        Object bean = EmptyConstructorBeanInjectionStrategy.createBeanWithoutInjection(clazz);
        for (Method method : methods) {
            Object paramObject;
            try {
                paramObject = BeanFactory.getBean(method.getParameters()[0].getType(), beanContainer);
            } catch (NoSuchElementException e) {

                return false;
            }
            try {
                method.setAccessible(true);
                method.invoke(bean, paramObject);
                method.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException("cannot invoke setter", e);
            }
        }
        beanContainer.put(clazz, bean);

        return true;
    }
}
