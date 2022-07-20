package com.example.lazyloaddemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class LazyLoadDemoApplicationTests {

	@Autowired
	ProjectRepository projectRepository;

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

}
