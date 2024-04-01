package org.example;

import org.example.controllers.Controller;
import org.example.di.Context;

public class Main {

    public static void main(String[] args){
        Context context = new Context("org.example");
        Controller bean = context.getBean(Controller.class);
        System.out.println(bean.execute());
    }
}
