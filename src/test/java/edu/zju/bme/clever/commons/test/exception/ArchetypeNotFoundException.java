package edu.zju.bme.clever.commons.test.exception;

public class ArchetypeNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3630238031176107623L;

	public ArchetypeNotFoundException() {
		super();
	}

	public ArchetypeNotFoundException(String message) {
		super(message);
	}

	public ArchetypeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArchetypeNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
