package net.atos.acelera.todo.enums;

public enum Priority {
	LOW, MEDIUM, HIGH;

	public static boolean contains(String value) {
		for (Priority priority : values()) {
			if (priority.name().equals(value)) {
				return true;
			}
		}
		return false;
	}
}
