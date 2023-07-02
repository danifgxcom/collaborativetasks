package com.danifgx.opensource.collaborativetasks.repository;

import com.danifgx.opensource.collaborativetasks.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByTitle(String title);
}
