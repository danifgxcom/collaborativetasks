package com.danifgx.opensource.collaborativetasks.data;

import com.danifgx.opensource.collaborativetasks.entity.Task;
import com.danifgx.opensource.collaborativetasks.entity.TaskList;
import com.danifgx.opensource.collaborativetasks.repository.TaskListRepository;
import com.danifgx.opensource.collaborativetasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TaskListRepository taskListRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public DataLoader(TaskListRepository taskListRepository, TaskRepository taskRepository) {
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        // Crear y guardar TaskLists
        TaskList taskList1 = new TaskList();
        taskList1.setTitle("Task List 1");
        taskListRepository.save(taskList1);

        TaskList taskList2 = new TaskList();
        taskList2.setTitle("Task List 2");
        taskListRepository.save(taskList2);

        // Crear y guardar Tasks
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description for Task 1");
        task1.setTaskList(taskList1);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description for Task 2");
        task2.setTaskList(taskList1);
        taskRepository.save(task2);
    }
}
