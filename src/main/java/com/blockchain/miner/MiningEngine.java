package com.blockchain.miner;

import org.slf4j.*;

import java.util.Arrays;

public class MiningEngine {

	private static final Logger logger = LoggerFactory.getLogger(MiningEngine.class);

	private BlockChainStorage storage;
	private BlockChain blockChain;
	private BlockPayloadService blockPayloadService;
	private static final int N_ZEROS = 5;

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

	public void start() throws HashCalculationException {
		BlockPayload payload = blockPayloadService.getPayloadForBlock();
		logger.info("Received payload for new block: " + payload.toString());
		Block block = Block.from(payload);
		block = transformBlockToReachDesiredHashSum(block);
		logger.info("Created desired block with hash: " + Arrays.toString(block.getHash().getHash()) + " and nonce: " + block.getNonce());
		//TODO add to block chain and go on

		//TODO start building until successful build or another miner sent his block
	}

	private Block transformBlockToReachDesiredHashSum(Block block) throws HashCalculationException {
		while (true) {
			if (block.getHash().isDesired(N_ZEROS)) {
				return block;
			} else {
				block.transformToCauseHashChange();
			}
		}
	}

}
