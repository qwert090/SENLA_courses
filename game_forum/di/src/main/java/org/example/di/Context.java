package org.example.di;

public class Context {
    private String packageName;

    public Context(String packageName) {
        this.packageName = packageName;

    }

    public <T> T bean(Class<T> clazz){
        BeanFactory beanFactory = new BeanFactory(packageName);
        return beanFactory.getBean(clazz);
    }

}
