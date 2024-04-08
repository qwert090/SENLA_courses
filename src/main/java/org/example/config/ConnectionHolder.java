package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConnectionHolder {
    private Map<String, Connection> connectionMap;
    private List<Connection> unusedConnections;

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    public ConnectionHolder() {
        connectionMap = new HashMap<>();
        unusedConnections = new ArrayList<>();
    }
}
