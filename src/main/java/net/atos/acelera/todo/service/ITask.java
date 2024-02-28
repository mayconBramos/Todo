package net.atos.acelera.todo.service;

import java.util.List;
import java.util.Optional;

import net.atos.acelera.todo.model.TaskModel;

public interface ITask {

	List<TaskModel> getAllTasks();

	Optional<TaskModel> getTaskById(String id);

	TaskModel saveTask(TaskModel task);

	void deleteTaskById(String id);

	List<TaskModel> getTasksByStatus(String status);

	List<TaskModel> getTasksByPriority(String priority);

	TaskModel updateTask(String taskId, TaskModel updatedTask);

}
