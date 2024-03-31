package org.example.di.autowire;

import org.example.di.BeanFactory;
import org.example.di.annotations.Autowire;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class FieldsBeanInjectionStrategy {

    public static boolean execute(Class<?> clazz, Map<Class<?>, Object> beanContainer) {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowire.class))
                .toList();
        Object bean = EmptyConstructorBeanInjectionStrategy.execute(clazz);
        for (Field field : fields) {
            Object fieldObject;
            try {
                fieldObject = BeanFactory.getBean(field.getType(), beanContainer);
            } catch (NoSuchElementException e) {

                return false;
            }
            try {
                field.setAccessible(true);
                field.set(bean, fieldObject);
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException("cannot set field", e);
            }
        }
        beanContainer.put(clazz, bean);

        return true;
    }
}
