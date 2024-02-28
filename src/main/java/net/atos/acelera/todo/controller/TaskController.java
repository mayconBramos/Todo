package net.atos.acelera.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.atos.acelera.todo.model.TaskModel;
import net.atos.acelera.todo.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/view")
	public String viewTasks(Model model) {
		List<TaskModel> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);

		if (!model.containsAttribute("task")) {
			model.addAttribute("task", new TaskModel());
		}

		return "tasks";
	}

	@GetMapping
	public ResponseEntity<List<TaskModel>> getAllTasks() {
		List<TaskModel> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<TaskModel> getTaskById(@PathVariable String taskId) {
		return taskService.getTaskById(taskId).map(task -> new ResponseEntity<>(task, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<TaskModel>> getTasksByStatus(@PathVariable String status) {
		List<TaskModel> tasks = taskService.getTasksByStatus(status);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping("/priority/{priority}")
	public ResponseEntity<List<TaskModel>> getTasksByPriority(@PathVariable String priority) {
		List<TaskModel> tasks = taskService.getTasksByPriority(priority);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createTask(@ModelAttribute TaskModel task) {
		taskService.saveTask(task);
		return "redirect:/tasks/view";
	}

	@PostMapping(value = "/update/{taskId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateTask(@PathVariable String taskId, @ModelAttribute TaskModel updatedTask) {
		taskService.updateTask(taskId, updatedTask);
		return "redirect:/tasks/view";
	}

	@GetMapping("/delete/{taskId}")
	public String deleteTask(@PathVariable String taskId) {

		taskService.deleteTaskById(taskId);
		return "redirect:/tasks/view";
	}
}
