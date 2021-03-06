package com.blockchain.miner.controller;

import com.blockchain.miner.application.IncomeApplicationService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(IncomeController.class)
@ContextConfiguration(classes = ControllerTestContextConfiguration.class)
class IncomeControllerIntegrationTest {

	private final static Logger logger = LoggerFactory.getLogger(IncomeControllerIntegrationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IncomeApplicationService applicationService;

	private static final Gson gson = new Gson();

	@Test
	void forValidRequestReturnsSuccess() throws Exception {
		when(applicationService.addIncomeBlock(any())).thenReturn(HandleBlockCreatedResult.success());
		BlockCreatedMessage message = BlockCreatedMessage.from("address", createImitationOfBlockDataMap(), "hash");
		String jsonMsg = gson.toJson(message);

		logger.info(jsonMsg);

		mockMvc.perform(MockMvcRequestBuilders.post("/income/blockcreated")
			.contentType("application/json;charset=UTF-8").content(jsonMsg))
			.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"));
	}

	private Map<String, String> createImitationOfBlockDataMap() {
		return new HashMap<>() {{
			put("0", "8ddd06d8-118f-47bc-9c3b-ab40d52b2632");
			put("1", "b967c0be-0076-407d-a14d-c9ad8a42afcc");
			put("2", "c1ab914a-2232-407e-b395-40433d55a026");
		}};
	}

	@Test
	void forWrongRequestReturnsError() throws Exception {
		when(applicationService.addIncomeBlock(any())).thenReturn(HandleBlockCreatedResult.success());
		WrongIncomeBlockCreatedRequestBody requestBody = new WrongIncomeBlockCreatedRequestBody();
		requestBody.setNodeAddress("address");
		requestBody.setBlockDataWrongName(createImitationOfBlockDataMap());
		String jsonMsg = gson.toJson(requestBody);

		logger.info(jsonMsg);

		mockMvc.perform(MockMvcRequestBuilders.post("/income/blockcreated")
			.contentType("application/json;charset=UTF-8").content(jsonMsg))
			.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest())
			.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("error"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").isNotEmpty());
	}

}