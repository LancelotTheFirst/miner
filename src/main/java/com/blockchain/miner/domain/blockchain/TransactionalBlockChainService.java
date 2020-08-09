package com.blockchain.miner.domain.blockchain;

import com.blockchain.miner.domain.block.Block;
import com.blockchain.miner.domain.distribution.BlockDistributionService;
import com.blockchain.miner.domain.income.IncomeDataService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Domain service to do transactional things with blockchain. Transactional methods should be outside of invoking class.
 */
public class TransactionalBlockChainService {

	private final BlockDistributionService blockDistributionService;
	private final IncomeDataService incomeDataService;
	private BlockChain blockChain;

	public TransactionalBlockChainService(BlockChain blockChain, BlockDistributionService blockDistributionService,
										  IncomeDataService incomeDataService) {
		this.blockChain = blockChain;
		this.blockDistributionService = blockDistributionService;
		this.incomeDataService = incomeDataService;
	}

	@Transactional
	public void addToBlockChainAndDistribute(Block block) {
		blockChain.addBlock(block);
		blockDistributionService.distributeBlock(block);
	}

	@Transactional
	public void addIncomeBlockToBlockChain(Block incomeBlock) {
		blockChain.addBlock(incomeBlock);
		incomeDataService.deleteBlock(incomeBlock);
	}

}
