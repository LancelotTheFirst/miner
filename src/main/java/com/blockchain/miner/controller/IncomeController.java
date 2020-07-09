package com.blockchain.miner.controller;

import com.blockchain.miner.service.IncomeApplicationService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Handles messages from other clients - these may be transactions or messages with newly created blocks
 */
@RestController
@RequestMapping("income")
public class IncomeController {

	private static final Logger logger = LoggerFactory.getLogger(IncomeController.class);

	private IncomeApplicationService applicationService;

	@PostMapping(value = "/blockcreated", consumes = "application/json", produces = "application/json")
	public HandleBlockCreatedResult handleBlockCreatedNotification(BlockCreatedMessage message) {
		HandleBlockCreatedResult result;
		try {
			checkAllRequiredFieldsAreFilled(message);

			result = applicationService.handleBlockCreatedNotification(message);
		} catch (RequiredIncomeDataEmptyException e) {
			logger.error("Node with address: " + message.getNodeAddress() + " sent this required fields:" +
				e.getEmptyFieldNames() + " as empty");
			result = HandleBlockCreatedResult.from(e);
		} catch (Exception e) {
			logger.error("Error while handling income block created notification from node with address: " + message.getNodeAddress());
			result = HandleBlockCreatedResult.from(e);
		}
		return result;
	}

	private void checkAllRequiredFieldsAreFilled(BlockCreatedMessage message) throws RequiredIncomeDataEmptyException {
		Collection<String> emptyFieldNames = new ArrayList<>();
		if (message.getNodeAddress() == null || message.getNodeAddress().isEmpty()) {
			emptyFieldNames.add("Node Address");
		}
		if (message.getBlockData() == null || message.getBlockData().isEmpty()) {
			emptyFieldNames.add("Block Data");
		}
		if (message.getHash() == null || message.getHash().isEmpty()) {
			emptyFieldNames.add("Hash");
		}
		if (!emptyFieldNames.isEmpty()) {
			throw new RequiredIncomeDataEmptyException(emptyFieldNames);
		}
	}

	@Autowired
	public void setApplicationService(IncomeApplicationService applicationService) {
		this.applicationService = applicationService;
	}
}
