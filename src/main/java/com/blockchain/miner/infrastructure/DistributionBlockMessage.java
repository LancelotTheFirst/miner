package com.blockchain.miner.infrastructure;

import com.blockchain.miner.domain.Block;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Map;

public class DistributionBlockMessage implements Serializable {
	private static final Gson gson = new Gson();
	private Map<String, String> data;
	private String hash;

	public static DistributionBlockMessage from(Block block) {
		DistributionBlockMessage message = new DistributionBlockMessage();
		message.setData(block.getData());
		message.setHash(block.getHash().asString());
		return message;
	}

	public static String toJson(DistributionBlockMessage blockMessage) {
		return gson.toJson(blockMessage);
	}

	public static DistributionBlockMessage fromJson(String blockMessageJson) {
		return gson.fromJson(blockMessageJson, DistributionBlockMessage.class);
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}
}
