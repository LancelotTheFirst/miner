package com.blockchain.miner.domain.income;

import com.blockchain.miner.domain.block.Block;

import java.util.Optional;

public interface IncomeDataService {
	Optional<Block> getIncomeBlock();

	void deleteBlock(Block block);
}
