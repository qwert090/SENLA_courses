package org.example.repositories;

import org.example.ParametersHolder;
import org.example.di.annotations.Autowire;
import org.example.di.annotations.Component;

@Component
public class RepositoryClass implements Repository {
    @Autowire
    private ParametersHolder parametersHolder;

    @Override
    public String execute(){
        return parametersHolder.getText();
    }
}
