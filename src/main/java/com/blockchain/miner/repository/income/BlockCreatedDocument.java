package com.blockchain.miner.repository.income;

import com.blockchain.miner.controller.BlockCreatedMessage;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "incomeblocks")
public class BlockCreatedDocument {

	@Id
	private String hash;
	private Map<String, String> blockData;
	private boolean savedToBlockChain;

	public static BlockCreatedDocument from(BlockCreatedMessage message) {
		BlockCreatedDocument document = new BlockCreatedDocument();
		document.setHash(message.getHash());
		document.setBlockData(message.getBlockData());
		document.setSavedToBlockChain(false);
		return document;
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
