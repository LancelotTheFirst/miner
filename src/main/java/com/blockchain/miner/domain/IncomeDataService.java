package com.blockchain.miner.domain;

import java.util.Optional;

public interface IncomeDataService {
	Optional<Block> getIncomeBlock();

	void deleteBlock(Block block);
}
