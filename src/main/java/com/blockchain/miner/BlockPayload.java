package com.blockchain.miner;

import java.util.Map;

public class BlockPayload {

	private Map<String, String> payloadMap;

	public static BlockPayload from(Map<String, String> payloadMap) {
		BlockPayload blockPayload = new BlockPayload();
		blockPayload.setPayloadMap(payloadMap);
		return blockPayload;
	}

	private void setPayloadMap(Map<String, String> payloadMap) {
		this.payloadMap = payloadMap;
	}
}
