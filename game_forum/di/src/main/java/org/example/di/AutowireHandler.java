package org.example.di;

import org.example.di.annotations.Autowire;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AutowireHandler {

    private Object createBeanWithFieldsInjection(Class<?> clazz, Map<Class<?>, Object> beanContainer) {

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowire.class))
                .toList();
        Object bean = createBeanWithoutInjection(clazz);
        for (Field field : fields) {
            Object fieldObject = beanContainer.get(field.getType());
            if (fieldObject == null) {
                return null;
            }
            try {
                field.setAccessible(true);
                field.set(bean, fieldObject);
                field.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException("cannot set field", e);
            }
        }
        return bean;
    }

    private Object createBeanWithConstructorInjection(Class<?> clazz) {
        List<Object> paramObjects = new ArrayList<>();
        Constructor<?> constructor = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(c -> c.isAnnotationPresent(Autowire.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no annotated constructor"));
        constructor.setAccessible(true);
        Class<?>[] parametersClasses = constructor.getParameterTypes();
        for (Class<?> paramClass : parametersClasses) {
            Object paramObject = BeanFactory.getBean(paramClass);
            if (paramObject == null) {
                return null;
            }
            paramObjects.add(paramObject);
        }
        try {
            return constructor.newInstance(paramObjects.toArray());
        } catch (Exception e) {
            throw new RuntimeException("cant create bean", e);
        }
    }

    private Object createBeanWithMethodsInjection(Class<?> clazz) {
        List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Autowire.class))
                .filter(method -> method.getParameterCount() == 1)
                .toList();
        Object bean = createBeanWithoutInjection(clazz);
        for (Method method : methods) {
            Object paramObject = BeanFactory.getBean(method.getParameters()[0].getType());
            if (paramObject == null) {
                return null;
            }
            try {
                method.setAccessible(true);
                method.invoke(bean, paramObject);
                method.setAccessible(false);
            } catch (Exception e) {
                throw new RuntimeException("cannot invoke setter", e);
            }
        }
        return bean;
    }

    private Object createBeanWithoutInjection(Class<?> clazz) {
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

    private List<AnnotatedElement> getAutowiredAnnotated(Function<Class<?>, AnnotatedElement[]> foo, Class<?> clazz) {
        return Arrays.stream(foo.apply(clazz))
                .filter(annotated -> annotated.isAnnotationPresent(Autowire.class))
                .toList();
    }

    private boolean createBean(Class<?> clazz, Map<Class<?>, Object> beanContainer) {
        var annotatedFields = getAutowiredAnnotated(Class::getDeclaredFields, clazz);
        var annotatedMethods = getAutowiredAnnotated(Class::getDeclaredMethods, clazz);
        var annotatedConstructors = getAutowiredAnnotated(Class::getDeclaredConstructors, clazz);

        if (!annotatedFields.isEmpty() && !annotatedMethods.isEmpty() ||
                !annotatedFields.isEmpty() && !annotatedConstructors.isEmpty() ||
                !annotatedMethods.isEmpty() && !annotatedConstructors.isEmpty()) {
            throw new RuntimeException("cannot mix injection type");
        }
        Object bean = null;
        if (!annotatedFields.isEmpty()) {
            bean = createBeanWithFieldsInjection(clazz, beanContainer);
        } else if (!annotatedMethods.isEmpty()) {
            bean = createBeanWithMethodsInjection(clazz);
        } else if (!annotatedConstructors.isEmpty()) {
            bean = createBeanWithConstructorInjection(clazz);
        } else {
            bean = createBeanWithoutInjection(clazz);
        }
        beanContainer.put(clazz, bean);
        return bean != null;
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
