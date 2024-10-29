package com.TaskTracker.categoryDetatils;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
