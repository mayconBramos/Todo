package net.atos.acelera.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.atos.acelera.todo.enums.Priority;
import net.atos.acelera.todo.enums.Status;
import net.atos.acelera.todo.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

	List<TaskModel> findByStatus(Status status);

	List<TaskModel> findByPriority(Priority priority);

}
