package com.nhantran.todoapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private ZonedDateTime createdTime;

    private ZonedDateTime modifiedTime;

    private boolean done;

    private ZonedDateTime completedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private List<Todo> todoList;

}
