package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConnectionHolder {
    private final Map<String, Connection> connectionMap;
    private final List<Connection> freeConnections;

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public ConnectionHolder() {
        connectionMap = new HashMap<>();
        freeConnections = new ArrayList<>();
    }

    public Connection getConnection(){
        String threadName =  Thread.currentThread().getName();
        try {
            if (connectionMap.containsKey(threadName)){
                return connectionMap.get(threadName);
            }
            Connection connection = null;
            if(freeConnections.size() > 0){
                for(Connection freeConnection : freeConnections)  {
                    connection = freeConnection;
                    if(!connection.isClosed()){
                        return connection;
                    }
                }
            }
            connection = DriverManager.getConnection(url, username, password);
            connectionMap.put(threadName, connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rollback() {
        try {
            String threadName = Thread.currentThread().getName();
            Connection connection = connectionMap.remove(threadName);
            connection.rollback();
            freeConnections.add(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void commit() {
        try {
            String threadName = Thread.currentThread().getName();
            Connection connection = connectionMap.remove(threadName);
            connection.commit();
            freeConnections.add(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            for (Connection connection: freeConnections){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
