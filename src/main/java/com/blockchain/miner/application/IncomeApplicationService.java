package com.blockchain.miner.application;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.repository.income.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncomeApplicationService {

	private IncomeDataRepository repository;

	public HandleBlockCreatedResult addIncomeBlock(BlockCreatedMessage message) {
		BlockCreatedDocument blockCreatedDocument = BlockCreatedDocument.from(message);
		repository.save(blockCreatedDocument);
		return HandleBlockCreatedResult.success();
	}

	@Autowired
	public void setRepository(IncomeDataRepository repository) {
		this.repository = repository;
	}

}
