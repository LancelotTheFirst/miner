package com.blockchain.miner.domain;

import com.blockchain.miner.repository.*;
import org.slf4j.*;

import java.util.Optional;

public class MiningEngine {

	private static final Logger logger = LoggerFactory.getLogger(MiningEngine.class);
	private static final int TEN_SECONDS = 10;

	private BlockChainRepository blockChainRepository;
	private IncomeDataRepository incomeDataRepository;
	private BlockChain blockChain;
	private BlockPayloadService blockPayloadService;
	private static final int N_ZEROS = 3;

	public static MiningEngine initializeFromRepository(BlockChainRepository blockChainRepository,
														BlockPayloadService blockPayloadService,
														IncomeDataRepository incomeDataRepository) {
		MiningEngine miningEngine = new MiningEngine();
		miningEngine.setBlockChainRepository(blockChainRepository);
		miningEngine.setIncomeDataRepository(incomeDataRepository);
		miningEngine.setBlockPayloadService(blockPayloadService);
		BlockChain blockChain = blockChainRepository.getBlockChain();
		miningEngine.setBlockChain(blockChain);
		return miningEngine;
	}

	public void setBlockChainRepository(BlockChainRepository storage) {
		this.blockChainRepository = storage;
	}

	public void setIncomeDataRepository(IncomeDataRepository incomeDataRepository) {
		this.incomeDataRepository = incomeDataRepository;
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

				Optional<Block> incomeBlockOptional = incomeDataRepository.getIncomeBlock();
				if (incomeBlockOptional.isPresent()) {
					blockChain.addBlock(incomeBlockOptional.get());
					logger.info("Added income block with hash: " + block.getHash().toString() + " and nonce: " + block.getNonce());
					break;
				} else if (block.isMined()) {
					blockChain.addBlock(block);
					logger.info("Created desired block with hash: " + block.getHash().toString() + " and nonce: " + block.getNonce());
					// TODO notify
					break;
				}
			}

		}


	}

	private void mineThisBlockForTimeInSeconds(Block block, int seconds) throws HashCalculationException {
		long current = System.currentTimeMillis();
		long end = current + seconds * 1000;
		while(System.currentTimeMillis() < end) {
			//logger.info("Hash: " + block.getHash().toString());
			if (block.getHash().isDesired(N_ZEROS)) {
				block.setMined(true);
				return;
			} else {
				block.transformToCauseHashChange();
			}
		}
	}

}
