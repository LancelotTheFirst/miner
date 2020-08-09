package com.blockchain.miner.application;

import com.blockchain.miner.domain.block.hash.HashCalculationException;
import com.blockchain.miner.domain.distribution.BlockDistributionService;
import com.blockchain.miner.domain.income.IncomeDataService;
import com.blockchain.miner.domain.mining.ProofOfWorkMiningEngine;
import com.blockchain.miner.domain.payload.BlockPayloadService;
import com.blockchain.miner.repository.blockchain.BlockChainRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiningEngineApplicationService {

	private BlockChainRepository blockChainRepository;
	private IncomeDataService incomeDataService;
	private BlockPayloadService blockPayloadService;
	private BlockDistributionService blockDistributionService;
	private static final Logger logger = LoggerFactory.getLogger(MiningEngineApplicationService.class);

	public void start() {
		ProofOfWorkMiningEngine proofOfWorkMiningEngine = ProofOfWorkMiningEngine.initializeFromRepository(
			blockChainRepository, blockPayloadService, incomeDataService, blockDistributionService);
		try {
			proofOfWorkMiningEngine.start();
		} catch (HashCalculationException e) {
			logger.error("Stopping mining because of error: " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Stopping mining because of unexpected error: " + e.getMessage(), e);
		}
	}

	@Autowired
	public void setBlockChainRepository(BlockChainRepository blockChainRepository) {
		this.blockChainRepository = blockChainRepository;
	}

	@Autowired
	public void setBlockPayloadService(BlockPayloadService blockPayloadService) {
		this.blockPayloadService = blockPayloadService;
	}

	@Autowired
	public void setIncomeDataService(IncomeDataService incomeDataService) {
		this.incomeDataService = incomeDataService;
	}

	@Autowired
	public void setBlockDistributionService(BlockDistributionService blockDistributionService) {
		this.blockDistributionService = blockDistributionService;
	}
}
