package org.example.di.autowire;

import org.example.di.annotations.Autowire;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AutowireHandler {

    private List<AnnotatedElement> getAutowiredAnnotated(Function<Class<?>, AnnotatedElement[]> foo, Class<?> clazz) {
        return Arrays.stream(foo.apply(clazz))
                .filter(annotated -> annotated.isAnnotationPresent(Autowire.class))
                .toList();
    }

    private boolean createBean(Class<?> clazz, Map<Class<?>, Object> beanContainer) {
        var annotatedFields = getAutowiredAnnotated(Class::getDeclaredFields, clazz);
        var annotatedMethods = getAutowiredAnnotated(Class::getDeclaredMethods, clazz);
        var annotatedConstructors = getAutowiredAnnotated(Class::getDeclaredConstructors, clazz);
        boolean created = false;
        if (!annotatedFields.isEmpty() && !annotatedMethods.isEmpty() ||
                !annotatedFields.isEmpty() && !annotatedConstructors.isEmpty() ||
                !annotatedMethods.isEmpty() && !annotatedConstructors.isEmpty()) {
            throw new RuntimeException("cannot mix injection type");
        }
        if (!annotatedFields.isEmpty()) {
            created = FieldsBeanInjectionStrategy.createBeanWithFieldsInjection(clazz, beanContainer);
        } else if (!annotatedMethods.isEmpty()) {
            created = MethodBeanInjectionStrategy.createBeanWithMethodsInjection(clazz, beanContainer);
        } else if (!annotatedConstructors.isEmpty()) {
            created = ConstructorBeanInjectionStrategy.createBeanWithConstructorInjection(clazz, beanContainer);
        } else {
            Object beanWithoutInjection = EmptyConstructorBeanInjectionStrategy.createBeanWithoutInjection(clazz);
            beanContainer.put(clazz, beanWithoutInjection);
            created = true;
        }
        return created;
    }

    public void createBeans(ArrayList<Class<?>> toCreate, Map<Class<?>, Object> beanContainer) {
        var progressList = (ArrayList<Class<?>>) toCreate.clone();
        for (Class<?> clazz : toCreate) {
            boolean created = createBean(clazz, beanContainer);
            if (created) {
                progressList.remove(clazz);
            }
        }
        if (progressList.size() == toCreate.size()) {
            throw new RuntimeException("cycle exception");
        } else if (progressList.size() != 0) {
            createBeans(progressList, beanContainer);
        }
    }
}
