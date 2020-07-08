package com.blockchain.miner.repository;

import com.blockchain.miner.domain.Block;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryIncomeDataRepository implements IncomeDataRepository {

	private Map<String, String> incomeBlockData = new HashMap<>();

	@Override
	public void saveIncomeBlockData(Map<String, String> blockData) {
		incomeBlockData = new HashMap<>(blockData);
	}

	@Override
	public Optional<Block> getIncomeBlock() {
		if (incomeBlockData.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(Block.fromIncomeBlockData(incomeBlockData));
		}

	}

	public Map<String, String> getIncomeBlockData() {
		return incomeBlockData;
	}
}
