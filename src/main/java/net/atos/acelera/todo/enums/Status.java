package net.atos.acelera.todo.enums;

public enum Status {
	PENDING, IN_PROGRESS, COMPLETED;

	public static boolean contains(String value) {
		for (Status status : values()) {
			if (status.name().equals(value)) {
				return true;
			}
		}
		return false;
	}
}
