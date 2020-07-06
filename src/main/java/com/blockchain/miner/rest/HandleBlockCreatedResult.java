package com.blockchain.miner.rest;

public class HandleBlockCreatedResult {

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
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
}
