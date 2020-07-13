package com.blockchain.miner.infrastructure;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.domain.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class HttpBlockDistributionService implements BlockDistributionService {

	private static final Logger logger = LoggerFactory.getLogger(HttpBlockDistributionService.class);

	private static final String CORE_URI = "http://localhost:";
	@Value("${server.port}")
	private String nodePort;
	@Value("${competitor.miner.port}")
	private String competitorMinerPort;
	@Value("${distribute.method.path}")
	private String methodPath;
	private Collection<String> portsToDistribute;

	@Override
	public void distributeBlock(Block block) {

		logger.info("node port is: " + nodePort);
		logger.info("competitor miner port is: " + competitorMinerPort);

		if (competitorMinerPort != null && !competitorMinerPort.isEmpty()) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			BlockCreatedMessage message = BlockCreatedMessage.from(CORE_URI + nodePort, block.getData(), block.getHash().toString());
			HttpEntity<BlockCreatedMessage> httpEntity = new HttpEntity<>(message, headers);
			HandleBlockCreatedResult result = restTemplate.postForObject(CORE_URI + competitorMinerPort + methodPath,
				httpEntity,
				HandleBlockCreatedResult.class);
			logger.info("Response from competitor miner is: " + result);
		}

	}

	private void distributeToAllKnownMiners(RestTemplate restTemplate, HttpEntity<BlockCreatedMessage> httpEntity) {
		Collection<HandleBlockCreatedResult> responses = Collections.emptyList();
		portsToDistribute.forEach(port -> responses.add(restTemplate.postForObject(CORE_URI + port, httpEntity,
			HandleBlockCreatedResult.class)));
		logger.info("Responses from another nodes are: " + responses.toString());
	}
}
