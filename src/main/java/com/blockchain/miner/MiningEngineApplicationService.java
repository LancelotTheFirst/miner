package com.blockchain.miner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiningEngineApplicationService {

	private BlockChainStorage blockChainStorage;
	private BlockPayloadService blockPayloadService;

	public void start() {
		MiningEngine miningEngine = MiningEngine.initializeFromStorage(blockChainStorage, blockPayloadService);
		miningEngine.start();
	}

	@Autowired
	public void setBlockChainStorage(BlockChainStorage blockChainStorage) {
		this.blockChainStorage = blockChainStorage;
	}

	@Autowired
	public void setBlockPayloadService(BlockPayloadService blockPayloadService) {
		this.blockPayloadService = blockPayloadService;
	}
}
