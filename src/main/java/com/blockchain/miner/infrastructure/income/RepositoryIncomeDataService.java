package com.blockchain.miner.infrastructure.income;

import com.blockchain.miner.domain.block.Block;
import com.blockchain.miner.domain.income.IncomeDataService;
import com.blockchain.miner.repository.income.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepositoryIncomeDataService implements IncomeDataService {

	private final IncomeDataRepository incomeDataRepository;

	public RepositoryIncomeDataService(IncomeDataRepository incomeDataRepository) {
		this.incomeDataRepository = incomeDataRepository;
	}

	@Override
	public Optional<Block> getIncomeBlock() {

		List<BlockCreatedDocument> blockDocuments = incomeDataRepository.findAll();
		if (!blockDocuments.isEmpty()) {
			BlockCreatedDocument document = blockDocuments.get(0);
			Block block = Block.fromIncomeBlock(document.getBlockData(), document.getHash());
			return Optional.of(block);
		}
		return Optional.empty();
	}

	@Override
	public void deleteBlock(Block block) {
		incomeDataRepository.deleteByHash(block.getHash().asString());
	}

}
