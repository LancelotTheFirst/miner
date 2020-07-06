package com.blockchain.miner.repository;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InMemoryIncomeDataRepository implements IncomeDataRepository {

	private Map<String, String> incomeBlockData;

	@Override
	public void saveIncomeBlockData(Map<String, String> blockData) {
		incomeBlockData = blockData;
	}

	public Map<String, String> getIncomeBlockData() {
		return incomeBlockData;
	}
}
