package com.blockchain.miner.rest;

import java.util.Map;

public class BlockCreatedMessage {

	private String nodeAddress;
	private Map<String, String> blockData;

	public String getNodeAddress() {
		return nodeAddress;
	}

	public void setNodeAddress(String nodeAddress) {
		this.nodeAddress = nodeAddress;
	}

	public Map<String, String> getBlockData() {
		return blockData;
	}

	public void setBlockData(Map<String, String> blockData) {
		this.blockData = blockData;
	}
}
