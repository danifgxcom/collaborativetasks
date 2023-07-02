package com.danifgx.opensource.collaborativetasks.controller;

import com.danifgx.opensource.collaborativetasks.entity.TaskList;
import com.danifgx.opensource.collaborativetasks.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isPresent()) {
            return ResponseEntity.ok(taskList.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList) {
        TaskList createdTaskList = taskListRepository.save(taskList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long id, @RequestBody TaskList taskList) {
        Optional<TaskList> existingTaskList = taskListRepository.findById(id);
        if (existingTaskList.isPresent()) {
            taskList.setId(id);
            TaskList updatedTaskList = taskListRepository.save(taskList);
            return ResponseEntity.ok(updatedTaskList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isPresent()) {
            taskListRepository.delete(taskList.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
