package com.rays.common;

import java.util.HashMap;

public class ORSResponse {

	private static final String INPUT_ERROR = "inputerror";
	private static final String MESSAGE = "message";
	private static final String DATA = "data";

	public HashMap<String, Object> result = new HashMap<String, Object>();

	private boolean success = false;

	public ORSResponse() {
	}

	public ORSResponse(boolean success) {
		this.success = success;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void addInputError(Object value) {
		result.put(INPUT_ERROR, value);
	}

	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}

	public void addData(Object value) {
		result.put(DATA, value);
	}

	public void addResult(String key, Object value) {
		result.put(key, value);
	}

}
