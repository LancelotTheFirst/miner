package com.blockchain.miner.infrastructure;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.domain.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.*;

@Component
public class AsyncHttpBlockDistributionService implements BlockDistributionService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncHttpBlockDistributionService.class);

	private static final String CORE_URI = "http://localhost:";
	@Value("${server.port}")
	private String nodePort;
	@Value("${competitor.miner.port}")
	private String competitorMinerPort;
	@Value("${distribute.method.path}")
	private String methodPath;
	private Collection<String> portsToDistribute;
	private JmsTemplate jmsTemplate;

	@Override
	public void distributeBlock(Block block) {
		DistributionBlockMessage blockMessage = DistributionBlockMessage.from(block);
		String jsonMessage = DistributionBlockMessage.toJson(blockMessage);
		jmsTemplate.convertAndSend("block-distribution-queue", jsonMessage);
	}

	@JmsListener(destination = "block-distribution-queue")
	private void asyncDistributeBlock(String blockMessageJson) {
		if (competitorMinerPort != null && !competitorMinerPort.isEmpty()) {
			DistributionBlockMessage blockMessage = DistributionBlockMessage.fromJson(blockMessageJson);
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			BlockCreatedMessage message = BlockCreatedMessage.from(CORE_URI + nodePort, blockMessage.getData(), blockMessage.getHash());
			HttpEntity<BlockCreatedMessage> httpEntity = new HttpEntity<>(message, headers);
			try {
				HandleBlockCreatedResult result = restTemplate.postForObject(CORE_URI + competitorMinerPort + methodPath,
					httpEntity,
					HandleBlockCreatedResult.class);
				logger.info("Response from competitor miner is: " + result);
			} catch (ResourceAccessException exception) {
				logger.error("Can not access remote node. Message is: " + exception.getMessage());
			}
		}
	}

	private void distributeToAllKnownMiners(RestTemplate restTemplate, HttpEntity<BlockCreatedMessage> httpEntity) {
		Collection<HandleBlockCreatedResult> responses = Collections.emptyList();
		portsToDistribute.forEach(port -> responses.add(restTemplate.postForObject(CORE_URI + port, httpEntity,
			HandleBlockCreatedResult.class)));
		logger.info("Responses from another nodes are: " + responses.toString());
	}

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
