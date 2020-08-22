package com.blockchain.miner.application;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.repository.income.IncomeDataRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest(classes = {IncomeApplicationService.class, IncomeDataRepository.class})
class IncomeApplicationServiceIntegrationTest {

	@Autowired
	private IncomeApplicationService applicationService;

	@MockBean
	private IncomeDataRepository repositoryMock;

	@Test
	void forCorrectMessageAddIncomeBlockReturnsSuccess() {
		BlockCreatedMessage message = BlockCreatedMessage.from("address", createImitationOfBlockDataMap(), "hash");

		HandleBlockCreatedResult result = applicationService.addIncomeBlock(message);

		Assert.assertEquals(HandleBlockCreatedResult.SUCCESS_STATUS, result.getStatus());
	}

	private Map<String, String> createImitationOfBlockDataMap() {
		return new HashMap<>() {{
			put("0", "8ddd06d8-118f-47bc-9c3b-ab40d52b2632");
			put("1", "b967c0be-0076-407d-a14d-c9ad8a42afcc");
			put("2", "c1ab914a-2232-407e-b395-40433d55a026");
		}};
	}

}