package org.example.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.example.config.ConnectionHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Aspect
@RequiredArgsConstructor
public class TransactionAspect {
    private final ConnectionHolder connectionHolder;

    @Pointcut("@annotation(org.example.annotation.Transaction)")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void before(){
        try {
            connectionHolder.getConnection().setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        Connection connection = connectionHolder.getConnection();
        try {
            connectionHolder.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        Connection connection = connectionHolder.getConnection();
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @After("pointcut()")
//    public void after(){
//        connectionHolder.close();
//    }
}
