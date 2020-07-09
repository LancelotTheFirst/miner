package com.blockchain.miner.infrastructure;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.domain.*;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class HttpBlockDistributionService implements BlockDistributionService {

	private static final Logger logger = LoggerFactory.getLogger(HttpBlockDistributionService.class);

	private static final String CORE_URI = "http://localhost:";
	// TODO read ports from properties
	private Integer nodePort;
	private Collection<Integer> portsToDistribute;

	@Override
	public void distributeBlock(Block block) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		BlockCreatedMessage message = BlockCreatedMessage.from(CORE_URI + nodePort, block.getData(), block.getHash().toString());
		HttpEntity<BlockCreatedMessage> httpEntity = new HttpEntity<>(message, headers);
		Collection<HandleBlockCreatedResult> responses = Collections.emptyList();
		portsToDistribute.forEach(port -> responses.add(restTemplate.postForObject(CORE_URI + port, httpEntity,
			HandleBlockCreatedResult.class)));
		logger.info("Responses from another nodes are: " + responses.toString());
	}
}
