package net.atos.acelera.todo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import net.atos.acelera.todo.enums.Priority;
import net.atos.acelera.todo.enums.Status;

@Data
@Entity
public class TaskModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String description;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "due_date")
	private LocalDate dueDate;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	private String category;

	private String assignee;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	public TaskModel(String description, LocalDate dueDate, Status status, Priority priority, String category,
			String assignee) {
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.priority = priority;
		this.category = category;
		this.assignee = assignee;
		this.creationDate = LocalDateTime.now();
	}

	public TaskModel() {
		super();
	}
}
