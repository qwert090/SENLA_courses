package org.example;

import org.example.controller.UserController;

public class ThreadStart implements Runnable{
    private final UserController userController;
    private final int threadId;

    public ThreadStart(UserController userController, int threadId){
        this.userController = userController;
        this.threadId = threadId;
    }

    @Override
    public synchronized void  run(){
        userController.createUser("""
                {
                    "nickname": "ALLAH",
                    "description": "Cool",
                    "avatar": "pic",
                    "totalExp": 1000000
                }
                """);
        System.out.println(userController.getById(1).toString());
        userController.updateUser("""
                {
                    "nickname": "Вася",
                    "description": "Cool",
                    "avatar": "pic",
                    "totalExp": 1000000
                }
                """);
    }
}
