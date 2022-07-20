# lazy-load-demo
Demo project for Spring Boot to show how lazy load work

# How it works?

Lazy loading is a pattern, that used to delay the initialization of an object as much as possible.

`FetchType.LAZY` is default value of property `fetch` for `OneToMany` relationship.

Look at entities classes

```java
@Entity()
public class Task {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private Project project;

    // ...
}
```

```java
@Entity
public class Project {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    // ...
}
```

One Project can have multiple Tasks.

When you pull up a Project, Task data is not initialized and loaded into memory until you make an explicit call.

Run `lazyLoad` test and look at logs.

```java
/* in LazyLoadDemoApplicationTests.java */
    // ...
	@Test
	@Transactional
	void lazyLoad() {
		Project project = projectRepository.findById(1L).orElse(null);
		Assertions.assertNotNull(project);
		System.err.println(project);
		List<Task> tasks = project.getTasks();
		Assertions.assertFalse(tasks.isEmpty());
		System.err.println(tasks);
	}
    // ...
```

Test passed and in logs we can see two SQL query:

```
Hibernate: select project0_.id as id1_0_0_, project0_.name as name2_0_0_ from project project0_ where project0_.id=?
Project{id=1, name='demo'}
Hibernate: select tasks0_.project_id as project_3_1_0_, tasks0_.id as id1_1_0_, tasks0_.id as id1_1_1_, tasks0_.description as descript2_1_1_, tasks0_.project_id as project_3_1_1_ from task tasks0_ where tasks0_.project_id=?
[Task(id=1, description=code, project=Project{id=1, name='demo'}), Task(id=2, description=deploy, project=Project{id=1, name='demo'})]
```

The second query was called `project.getTasks()`.

All of this means that lazy loading can make an extra request to the database when it's not obvious.
