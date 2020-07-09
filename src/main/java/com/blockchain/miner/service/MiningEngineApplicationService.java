package com.blockchain.miner.service;

import com.blockchain.miner.domain.*;
import com.blockchain.miner.repository.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiningEngineApplicationService {

	private BlockChainRepository blockChainRepository;
	private IncomeDataRepository incomeDataRepository;
	private BlockPayloadService blockPayloadService;
	private BlockDistributionService blockDistributionService;
	private static final Logger logger = LoggerFactory.getLogger(MiningEngineApplicationService.class);

	public void start() {
		ProofOfWorkMiningEngine proofOfWorkMiningEngine = ProofOfWorkMiningEngine.initializeFromRepository(
			blockChainRepository, blockPayloadService, incomeDataRepository, blockDistributionService);
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
	public void setIncomeDataRepository(IncomeDataRepository incomeDataRepository) {
		this.incomeDataRepository = incomeDataRepository;
	}

	@Autowired
	public void setBlockDistributionService(BlockDistributionService blockDistributionService) {
		this.blockDistributionService = blockDistributionService;
	}
}
