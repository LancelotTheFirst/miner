package com.blockchain.miner.domain;

import com.blockchain.miner.repository.BlockChainRepository;
import org.slf4j.*;

public class MiningEngine {

	private static final Logger logger = LoggerFactory.getLogger(MiningEngine.class);
	private static final int TEN_SECONDS = 10;

	private BlockChainRepository blockChainRepository;
	private BlockChain blockChain;
	private BlockPayloadService blockPayloadService;
	private static final int N_ZEROS = 2;

	public static MiningEngine initializeFromRepository(BlockChainRepository blockChainRepository,
														BlockPayloadService blockPayloadService) {
		MiningEngine miningEngine = new MiningEngine();
		miningEngine.setRepository(blockChainRepository);
		miningEngine.setBlockPayloadService(blockPayloadService);
		BlockChain blockChain = blockChainRepository.getBlockChain();
		miningEngine.setBlockChain(blockChain);
		return miningEngine;
	}

	public void setRepository(BlockChainRepository storage) {
		this.blockChainRepository = storage;
	}

	private void setBlockPayloadService(BlockPayloadService blockPayloadService) {
		this.blockPayloadService = blockPayloadService;
	}

	private void setBlockChain(BlockChain blockChain) {
		this.blockChain = blockChain;
	}

	public void start() throws HashCalculationException {
		while (true) {
			BlockPayload payload = blockPayloadService.getPayloadForBlock();
			logger.info("Received payload for new block: " + payload.toString());
			Block block = Block.from(payload);
			while (true) {
				mineThisBlockForTimeInSeconds(block, TEN_SECONDS);

				// go to the income data repository to check for blocks

				// check
				// add external if exists
					// break
				if (block.isMined()) {
					blockChain.addBlock(block);
					break;
				}
				// mine for some time
				// check for another miner block
				// if received block than add it to chain and start from scratch
				// go on
			}



			blockChain.addBlock(block);
			logger.info("Created desired block with hash: " + block.getHash().toString() + " and nonce: " + block.getNonce());
		}

		// TODO notify
	}

	private void mineThisBlockForTimeInSeconds(Block block, int seconds) throws HashCalculationException {
		long current = System.currentTimeMillis();
		long end = current + seconds * 1000;
		while(System.currentTimeMillis() < end) {
			logger.info("Hash: " + block.getHash().toString());
			if (block.getHash().isDesired(N_ZEROS)) {
				block.setMined(true);
				return;
			} else {
				block.transformToCauseHashChange();
			}
		}
	}

}
