package com.blockchain.miner;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiningEngineApplicationService {

	private BlockChainStorage blockChainStorage;
	private BlockPayloadService blockPayloadService;
	private static final Logger logger = LoggerFactory.getLogger(MiningEngineApplicationService.class);

	public void start() {
		MiningEngine miningEngine = MiningEngine.initializeFromStorage(blockChainStorage, blockPayloadService);
		try {
			miningEngine.start();
		} catch (HashCalculationException e) {
			logger.error("Stopping mining because of error: " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Stopping mining because of unexpected error: " + e.getMessage(), e);
		}
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
