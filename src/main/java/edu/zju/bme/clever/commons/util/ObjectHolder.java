package edu.zju.bme.clever.commons.util;

public class ObjectHolder<T> {
	private T value = null;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean isNull() {
		return this.value == null;
	}
}
