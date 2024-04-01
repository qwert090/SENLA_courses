package org.example.di;

public class Context {
    private String packageName;
    private final BeanFactory beanFactory;

    public Context(String packageName) {
        this.packageName = packageName;
        beanFactory = new BeanFactory(packageName);
    }

    public <T> T getBean(Class<T> clazz) {
        return BeanFactory.getBean(clazz, beanFactory.getBeanContainer());
    }
}
