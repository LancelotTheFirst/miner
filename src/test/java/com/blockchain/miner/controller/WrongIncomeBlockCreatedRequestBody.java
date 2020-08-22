package com.blockchain.miner.controller;

import java.util.Map;

public class WrongIncomeBlockCreatedRequestBody {

	private String nodeAddress;
	private Map<String, String> blockDataWrongName;
	// hash field is absent


	public void setNodeAddress(String nodeAddress) {
		this.nodeAddress = nodeAddress;
	}

	public void setBlockDataWrongName(Map<String, String> wrongName) {
		this.blockDataWrongName = wrongName;
	}
}
