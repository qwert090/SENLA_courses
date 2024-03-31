package org.example.controllers;

import org.example.di.annotations.Autowire;
import org.example.di.annotations.Component;
import org.example.services.Service;

@Component
public class Controller {
    private Service service;

    @Autowire
    public Controller(Service service) {
        this.service = service;
    }

    public String execute() {
        return service.execute();
    }
}
