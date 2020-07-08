package com.blockchain.miner.repository;

import com.blockchain.miner.domain.Block;

import java.util.*;

public interface IncomeDataRepository {

	void saveIncomeBlockData(Map<String, String> blockData);

	Optional<Block> getIncomeBlock();
}
