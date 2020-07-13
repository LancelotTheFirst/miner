package com.blockchain.miner.domain;

import com.blockchain.miner.repository.*;
import org.slf4j.*;

import java.util.Optional;

public class ProofOfWorkMiningEngine {

	private static final Logger logger = LoggerFactory.getLogger(ProofOfWorkMiningEngine.class);
	private static final int TEN_SECONDS = 10;

	private BlockChainRepository blockChainRepository;
	private IncomeDataRepository incomeDataRepository;
	private BlockChain blockChain;
	private BlockPayloadService blockPayloadService;
	private static final int N_ZEROS = 5;
	private BlockDistributionService blockDistributionService;

	public static ProofOfWorkMiningEngine initializeFromRepository(BlockChainRepository blockChainRepository,
																   BlockPayloadService blockPayloadService,
																   IncomeDataRepository incomeDataRepository,
																   BlockDistributionService blockDistributionService) {
		ProofOfWorkMiningEngine proofOfWorkMiningEngine = new ProofOfWorkMiningEngine();
		proofOfWorkMiningEngine.setBlockChainRepository(blockChainRepository);
		proofOfWorkMiningEngine.setIncomeDataRepository(incomeDataRepository);
		proofOfWorkMiningEngine.setBlockPayloadService(blockPayloadService);
		proofOfWorkMiningEngine.setBlockDistributionService(blockDistributionService);
		BlockChain blockChain = blockChainRepository.getBlockChain();
		proofOfWorkMiningEngine.setBlockChain(blockChain);
		return proofOfWorkMiningEngine;
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

	public void setBlockDistributionService(BlockDistributionService blockDistributionService) {
		this.blockDistributionService = blockDistributionService;
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
				logger.info("Current state of blockchain: " + blockChain.toString());
				mineThisBlockForTimeInSeconds(block, TEN_SECONDS);
				Optional<Block> incomeBlockOptional = incomeDataRepository.getIncomeBlock();
				if (incomeBlockOptional.isPresent()) {
					Block incomeBlock = incomeBlockOptional.get();
					blockChain.addBlock(incomeBlock);
					logger.info("Added income block with hash: " + incomeBlock.getHash().toString() + " and nonce: " + incomeBlock.getNonce());
					break;
				} else if (block.isMined()) {
					blockChain.addBlock(block);
					logger.info("Created desired block with hash: " + block.getHash().toString() + " and nonce: " + block.getNonce());
					blockDistributionService.distributeBlock(block);
					break;
				}

			}

		}


	}

	private void mineThisBlockForTimeInSeconds(Block block, int seconds) throws HashCalculationException {
		long current = System.currentTimeMillis();
		long end = current + seconds * 1000;
		while(System.currentTimeMillis() < end) {
			if (block.getHash().isDesired(N_ZEROS)) {
				block.setMined(true);
				return;
			} else {
				block.transformToCauseHashChange();
			}
		}
	}

}
