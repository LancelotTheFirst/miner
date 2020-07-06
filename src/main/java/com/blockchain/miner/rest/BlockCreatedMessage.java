package com.blockchain.miner.rest;

import java.util.Map;

public class BlockCreatedMessage {

	private String nodeAddress;
	private Map<String, String> payload;

	public String getNodeAddress() {
		return nodeAddress;
	}

	public void setNodeAddress(String nodeAddress) {
		this.nodeAddress = nodeAddress;
	}

	public Map<String, String> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
}
