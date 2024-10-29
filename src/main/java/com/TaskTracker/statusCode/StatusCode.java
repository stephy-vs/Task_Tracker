package com.TaskTracker.statusCode;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "statusCode")
public class StatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;
}
