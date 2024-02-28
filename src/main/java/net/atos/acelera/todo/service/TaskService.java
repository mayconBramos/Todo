package net.atos.acelera.todo.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import net.atos.acelera.todo.enums.Priority;
import net.atos.acelera.todo.enums.Status;
import net.atos.acelera.todo.model.TaskModel;
import net.atos.acelera.todo.repository.TaskRepository;

@Service
@Transactional
public class TaskService implements ITask {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<TaskModel> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Optional<TaskModel> getTaskById(String id) {
		return taskRepository.findById(Long.valueOf(id));
	}

	@Override
	public TaskModel saveTask(TaskModel task) {
		validateTaskEnums(task);
		task.setCreationDate(LocalDateTime.now());
		return taskRepository.save(task);
	}
	
	@Override
	public TaskModel updateTask(String taskId, TaskModel updatedTask) {
		
        Optional<TaskModel> existingTaskOptional = taskRepository.findById(Long.valueOf(taskId));

        if (existingTaskOptional.isPresent()) {
            TaskModel existingTask = existingTaskOptional.get();

            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setCategory(updatedTask.getCategory());
            existingTask.setAssignee(updatedTask.getAssignee());

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Tarefa não encontrada com ID: " + taskId);
        }
    }

	@Override
	public void deleteTaskById(String id) {
		taskRepository.deleteById(Long.valueOf(id));
	}

	@Override
	public List<TaskModel> getTasksByStatus(String status) {
		Status statusEnum = Status.valueOf(status.toUpperCase());
		validateEnum(Status.values(), statusEnum, "Status");
		return taskRepository.findByStatus(statusEnum);
	}

	@Override
	public List<TaskModel> getTasksByPriority(String priority) {
		Priority priorityEnum = Priority.valueOf(priority.toUpperCase());
		validateEnum(Priority.values(), priorityEnum, "Priority");
		return taskRepository.findByPriority(priorityEnum);
	}

	private <T extends Enum<T>> void validateEnum(T[] values, T enumValue, String fieldName) {
		Assert.isTrue(Arrays.asList(values).contains(enumValue), fieldName + " inválido: " + enumValue);
	}

	private void validateTaskEnums(TaskModel task) {
		validateEnum(Status.values(), task.getStatus(), "Status");
		validateEnum(Priority.values(), task.getPriority(), "Priority");
	}
}
