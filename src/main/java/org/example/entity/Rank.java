package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rank {
    private long id;
    private String name;
    private String description;
}
