package com.blockchain.miner.controller;

import java.util.Map;

public class BlockCreatedMessage {

	private String nodeAddress;
	private Map<String, String> blockData;
	private String hash;

	public static BlockCreatedMessage from(String nodeAddress, Map<String, String> blockData, String hash) {
		BlockCreatedMessage message = new BlockCreatedMessage();
		message.setNodeAddress(nodeAddress);
		message.setBlockData(blockData);
		message.setHash(hash);
		return message;
	}

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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "BlockCreatedMessage{" +
			"nodeAddress='" + nodeAddress + '\'' +
			", blockData=" + blockData +
			", hash='" + hash + '\'' +
			'}';
	}
}
