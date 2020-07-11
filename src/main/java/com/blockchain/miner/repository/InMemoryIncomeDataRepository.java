package com.blockchain.miner.repository;

import com.blockchain.miner.domain.Block;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Now it can save only one last block - not thread safe
 */
@Component
public class InMemoryIncomeDataRepository implements IncomeDataRepository {

	public static final String EMPTY = "";
	private Map<String, String> incomeBlockData = new HashMap<>();
	private String hash;

	@Override
	public void saveIncomeBlock(Map<String, String> incomeBlockData, String hash) {
		this.incomeBlockData = new HashMap<>(incomeBlockData);
		this.hash = hash;
	}

	@Override
	public Optional<Block> getIncomeBlock() {
		if (incomeBlockData.isEmpty()) {
			return Optional.empty();
		} else {
			Optional<Block> blockOptional = Optional.of(Block.fromIncomeBlock(incomeBlockData, hash));
			clear();
			return blockOptional;
		}

	}

	private void clear() {
		incomeBlockData = new HashMap<>();
		hash = EMPTY;
	}

	public Map<String, String> getIncomeBlockData() {
		return incomeBlockData;
	}

	public String getHash() {
		return hash;
	}
}
