package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Getter @Setter
@Table(name="APP_USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="AGE", nullable=false)
    private Integer age;

    @Column(name="SALARY", nullable=true)
    private double salary;

}
