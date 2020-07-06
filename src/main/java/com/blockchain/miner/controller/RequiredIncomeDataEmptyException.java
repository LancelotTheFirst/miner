package com.blockchain.miner.controller;

import java.util.Collection;

public class RequiredIncomeDataEmptyException extends Exception {

	private final Collection<String> emptyFieldNames;

	public RequiredIncomeDataEmptyException(Collection<String> emptyFieldNames) {
		super("Required fields '" + emptyFieldNames + "' is empty");
		this.emptyFieldNames = emptyFieldNames;
	}

	public Collection<String> getEmptyFieldNames() {
		return emptyFieldNames;
	}
}
