package com.blockchain.miner.domain.distribution;

import com.blockchain.miner.domain.block.Block;

public interface BlockDistributionService {
	void distributeBlock(Block block);
}
