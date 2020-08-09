package com.blockchain.miner.repository.income;

import com.blockchain.miner.controller.BlockCreatedMessage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "incomeblocks")
public class BlockCreatedEntity {

	@Id
	private String hash;
	private Map<String, String> blockData;
	private boolean savedToBlockChain;

	public static BlockCreatedEntity from(BlockCreatedMessage message) {
		BlockCreatedEntity entity = new BlockCreatedEntity();
		entity.setHash(message.getHash());
		entity.setBlockData(message.getBlockData());
		entity.setSavedToBlockChain(false);
		return entity;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHash() {
		return hash;
	}

	public void setBlockData(Map<String, String> blockData) {
		this.blockData = blockData;
	}

	public Map<String, String> getBlockData() {
		return blockData;
	}

	public void setSavedToBlockChain(boolean savedToBlockChain) {
		this.savedToBlockChain = savedToBlockChain;
	}

	public boolean getSavedToBlockChain() {
		return savedToBlockChain;
	}
}
