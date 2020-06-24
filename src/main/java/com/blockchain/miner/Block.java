package com.blockchain.miner;

import org.slf4j.*;

import java.util.Map;

public class Block {

	private static final Logger logger = LoggerFactory.getLogger(Block.class);
	private static final String NONCE_MAP_KEY_NAME = "nonce";
	private Map<String, String> data;
	private long nonce = 0;
	private Hash hash;

	public static Block from(BlockPayload payload) throws HashCalculationException {
		Block block = new Block();
		block.setData(payload.getPayloadMap());
		block.setHash(Hash.from(block));
		return block;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void transformToCauseHashChange() throws HashCalculationException {
		data.put(NONCE_MAP_KEY_NAME, String.valueOf(nonce));
		nonce++;
		hash.recalculateHash(this);
	}

	public String getNonce() {
		return data.get(NONCE_MAP_KEY_NAME);
	}

	public void setHash(Hash hash) {
		this.hash = hash;
	}

	public Hash getHash() {
		return hash;
	}
}
