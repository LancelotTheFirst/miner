package com.blockchain.miner.controller;

public class HandleBlockCreatedResult {

	public static final String SUCCESS_STATUS = "success";
	public static final String ERROR_STATUS = "error";
	public static final String EMPTY = "";
	private String status;
	private String message;

	public static HandleBlockCreatedResult from(RequiredIncomeDataEmptyException e) {
		HandleBlockCreatedResult result = new HandleBlockCreatedResult();
		result.setStatus(ERROR_STATUS);
		result.setMessage("Required fields: " + e.getEmptyFieldNames() + " are empty");
		return result;
	}

	public static HandleBlockCreatedResult from(Exception e) {
		HandleBlockCreatedResult result = new HandleBlockCreatedResult();
		result.setStatus(ERROR_STATUS);
		result.setMessage("Unexpected internal error with message: " + e.getMessage());
		return result;
	}

	public static HandleBlockCreatedResult success() {
		HandleBlockCreatedResult result = new HandleBlockCreatedResult();
		result.setStatus(SUCCESS_STATUS);
		result.setMessage(EMPTY);
		return result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HandleBlockCreatedResult{" +
			"status='" + status + '\'' +
			", message='" + message + '\'' +
			'}';
	}
}
