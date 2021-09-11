package com.neshan.rajmanint.Entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {
    private long id;
    private String name;
}
