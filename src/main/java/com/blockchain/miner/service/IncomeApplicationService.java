package com.blockchain.miner.service;

import com.blockchain.miner.repository.IncomeDataRepository;
import com.blockchain.miner.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncomeApplicationService {

	private IncomeDataRepository repository;

	public HandleBlockCreatedResult handleBlockCreatedNotification(BlockCreatedMessage message) {
		repository.saveIncomeBlockData(message.getBlockData());
		return HandleBlockCreatedResult.success();
	}

	@Autowired
	public void setRepository(IncomeDataRepository repository) {
		this.repository = repository;
	}

}
