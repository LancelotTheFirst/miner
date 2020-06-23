package com.blockchain.miner;

public class MiningEngine {

	private BlockChainStorage storage;
	private BlockChain blockChain;
	private BlockPayloadService blockPayloadService;

	public static MiningEngine initializeFromStorage(BlockChainStorage blockChainStorage,
													 BlockPayloadService blockPayloadService) {
		MiningEngine miningEngine = new MiningEngine();
		miningEngine.setStorage(blockChainStorage);
		miningEngine.setBlockPayloadService(blockPayloadService);
		BlockChain blockChain = blockChainStorage.getBlockChain();
		miningEngine.setBlockChain(blockChain);
		return miningEngine;
	}

	public void setStorage(BlockChainStorage storage) {
		this.storage = storage;
	}

	private void setBlockPayloadService(BlockPayloadService blockPayloadService) {
		this.blockPayloadService = blockPayloadService;
	}

	private void setBlockChain(BlockChain blockChain) {
		this.blockChain = blockChain;
	}

	public void start() {
		BlockPayload payload = blockPayloadService.getPayloadForBlock();

		//TODO start building until successful build or another miner sent his block
	}

}
