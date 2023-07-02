package com.danifgx.opensource.collaborativetasks.repository;

import com.danifgx.opensource.collaborativetasks.entity.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TaskListRepositoryTest {

    private final TestEntityManager entityManager;
    private final TaskListRepository taskListRepository;
    @Autowired
    public TaskListRepositoryTest(TestEntityManager entityManager, TaskListRepository taskListRepository) {
        this.entityManager = entityManager;
        this.taskListRepository = taskListRepository;
    }

    @Test
    public void testSaveTaskList() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Test Task List");

        TaskList savedTaskList = taskListRepository.save(taskList);
        assertNotNull(savedTaskList.getId());
        assertEquals("Test Task List", savedTaskList.getTitle());
    }

    @Test
    public void testFindById() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Test Task List");

        TaskList savedTaskList = taskListRepository.save(taskList);
        Long taskListId = savedTaskList.getId();

        TaskList foundTaskList = taskListRepository.findById(taskListId).orElse(null);
        assertNotNull(foundTaskList);
        assertEquals(taskListId, foundTaskList.getId());
        assertEquals("Test Task List", foundTaskList.getTitle());
    }

    @Test
    public void testFindByTitle() {
        TaskList taskList1 = new TaskList();
        taskList1.setTitle("Task List 1");
        taskListRepository.save(taskList1);

        TaskList taskList2 = new TaskList();
        taskList2.setTitle("Task List 2");
        taskListRepository.save(taskList2);

        List<TaskList> foundTaskLists = taskListRepository.findByTitle("Task List 1");
        assertNotNull(foundTaskLists);
        assertEquals(1, foundTaskLists.size());
        assertEquals("Task List 1", foundTaskLists.get(0).getTitle());
    }

    @Test
    public void testDeleteTaskList() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Test Task List");
        TaskList savedTaskList = taskListRepository.save(taskList);

        Long taskListId = savedTaskList.getId();

        taskListRepository.deleteById(taskListId);

        assertFalse(taskListRepository.existsById(taskListId));
    }
}
