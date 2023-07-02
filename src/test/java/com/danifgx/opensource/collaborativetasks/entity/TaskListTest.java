package com.danifgx.opensource.collaborativetasks.entity;

import com.danifgx.opensource.collaborativetasks.repository.TaskListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TaskListTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskListRepository taskListRepository;

    @Test
    public void testCreateTaskList() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Test Task List");

        TaskList savedTaskList = taskListRepository.save(taskList);
        assertNotNull(savedTaskList.getId());
        assertEquals("Test Task List", savedTaskList.getTitle());
    }


    @Test
    public void testTaskListTasks() {
        TaskList taskList = new TaskList();
        taskList.setTitle("My Task List");
        taskList.setDescription("This is a task list");

        Task task1 = new Task();
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setTitle("Task 2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        taskList.setTasks(tasks);

        assertEquals(2, taskList.getTasks().size());
        assertTrue(taskList.getTasks().contains(task1));
        assertTrue(taskList.getTasks().contains(task2));
    }

    // Other tests for the TaskList entity
}
