package com.blockchain.miner.service;

import com.blockchain.miner.rest.*;
import org.springframework.stereotype.Component;

@Component
public class IncomeApplicationService {

	public HandleBlockCreatedResult handleBlockCreatedNotification(BlockCreatedMessage message) {
		return null;
	}

	// put to the storage - use inMemory storage

}
