package org.example.services;

import org.example.di.annotations.Autowire;
import org.example.di.annotations.Component;
import org.example.repositories.Repository;

@Component
public class ServiceClass implements Service {
    private Repository repository;

    @Autowire
    public void setRepository(Repository repository){
        this.repository = repository;
    }

    @Override
    public String execute(){
        return repository.execute();
    }
}
