package com.blockchain.miner.repository;

import com.blockchain.miner.domain.Block;

import java.util.*;

public interface IncomeDataRepository {

	void saveIncomeBlock(Map<String, String> blockData, String hash);

	Optional<Block> getIncomeBlock();
}
