package com.danifgx.opensource.collaborativetasks.repository;

import com.danifgx.opensource.collaborativetasks.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TaskRepositoryTest {

    private final TestEntityManager entityManager;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskRepositoryTest(TestEntityManager entityManager, TaskRepository taskRepository) {
        this.entityManager = entityManager;
        this.taskRepository = taskRepository;
    }

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setTitle("Test Task");

        Task savedTask = taskRepository.save(task);
        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
    }

    @Test
    public void testFindById() {
        Task task = new Task();
        task.setTitle("Test Task");

        Task savedTask = entityManager.persistAndFlush(task);
        Long taskId = savedTask.getId();

        Task foundTask = taskRepository.findById(taskId).orElse(null);
        assertNotNull(foundTask);
        assertEquals(taskId, foundTask.getId());
        assertEquals("Test Task", foundTask.getTitle());
    }

    @Test
    public void testFindByTitle() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        entityManager.persistAndFlush(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        entityManager.persistAndFlush(task2);

        List<Task> foundTasks = taskRepository.findByTitle("Task 1");
        assertNotNull(foundTasks);
        assertEquals(1, foundTasks.size());
        assertEquals("Task 1", foundTasks.get(0).getTitle());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        entityManager.persistAndFlush(task);

        Long taskId = task.getId();

        taskRepository.deleteById(taskId);

        assertFalse(taskRepository.existsById(taskId));
    }


}
