package com.example.lazyloaddemo;

import lombok.*;

import javax.persistence.*;

@Entity(name = "task")
@Table(name = "TASK")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Task {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    public Task(Long id, String description, Project project) {
        this.id = id;
        this.description = description;
        this.project = project;
    }
}
