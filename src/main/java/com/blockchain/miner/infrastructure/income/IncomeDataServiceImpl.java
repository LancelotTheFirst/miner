package com.blockchain.miner.infrastructure.income;

import com.blockchain.miner.domain.block.Block;
import com.blockchain.miner.domain.income.IncomeDataService;
import com.blockchain.miner.repository.income.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IncomeDataServiceImpl implements IncomeDataService {

	private IncomeDataRepository incomeDataRepository;

	@Override
	public Optional<Block> getIncomeBlock() {
		List<BlockCreatedEntity> blockEntities = incomeDataRepository.findAll();
		if (!blockEntities.isEmpty()) {
			BlockCreatedEntity entity = blockEntities.get(0);
			Block block = Block.fromIncomeBlock(entity.getBlockData(), entity.getHash());
			return Optional.of(block);
		}
		return Optional.empty();
	}

	@Override
	public void deleteBlock(Block block) {
		incomeDataRepository.deleteByHash(block.getHash().asString());
	}

	@Autowired
	public void setIncomeDataRepository(IncomeDataRepository incomeDataRepository) {
		this.incomeDataRepository = incomeDataRepository;
	}
}