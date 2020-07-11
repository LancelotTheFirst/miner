package com.blockchain.miner.controller;

import com.blockchain.miner.service.IncomeApplicationService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

	@PostMapping(value = "/blockcreated")
	public ResponseEntity<HandleBlockCreatedResult> addCreatedBlock(@RequestBody BlockCreatedMessage message) {
		try {
			logger.info("Received income message: " + message.toString());
			checkAllRequiredFieldsAreFilled(message);
			HandleBlockCreatedResult result = applicationService.addIncomeBlock(message);
			return ResponseEntity.ok(result);
		} catch (RequiredIncomeDataEmptyException e) {
			logger.error("Node with address: " + message.getNodeAddress() + " sent this required fields:" +
				e.getEmptyFieldNames() + " as empty");
			HandleBlockCreatedResult result = HandleBlockCreatedResult.from(e);
			return ResponseEntity.badRequest().body(result);
		} catch (Exception e) {
			logger.error("Error while handling income block created notification from node with address: " + message.getNodeAddress());
			HandleBlockCreatedResult result = HandleBlockCreatedResult.from(e);
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
