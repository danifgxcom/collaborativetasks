package com.danifgx.opensource.collaborativetasks.repository;

import com.danifgx.opensource.collaborativetasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitle(String title);
}