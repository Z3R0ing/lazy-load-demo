package com.example.lazyloaddemo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "project")
@Table(name = "PROJECT")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Project {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project(Long id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
