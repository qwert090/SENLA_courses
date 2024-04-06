package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Role {
    private long id;
    private String name;
    private String description;
    private List<Credentials> credentials;
}
