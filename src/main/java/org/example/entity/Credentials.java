package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Credentials {
    private long id;
    private String password;
    private String email;
    private List<Role> role;
}
