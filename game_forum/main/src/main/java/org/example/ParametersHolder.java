package org.example;

import org.example.di.annotations.Component;
import org.example.di.annotations.Value;

@Component
public class ParametersHolder {

    @Value(value = "my.param.db")
    private String text;

    public String getText(){
        return text;
    }
}
