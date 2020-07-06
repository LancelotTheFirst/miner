package com.blockchain.miner.repository;

import java.util.Map;

public interface IncomeDataRepository {

	void saveIncomeBlockData(Map<String, String> blockData);

}
