package com.blockchain.miner.infrastructure.distribution;

import com.blockchain.miner.controller.*;
import com.blockchain.miner.domain.block.Block;
import com.blockchain.miner.domain.distribution.BlockDistributionService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

@Component
public class AsyncHttpBlockDistributionService implements BlockDistributionService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncHttpBlockDistributionService.class);

	private JmsTemplate jmsTemplate;

	@Override
	public void distributeBlock(Block block) {
		DistributionBlockMessage blockMessage = DistributionBlockMessage.from(block);
		String jsonMessage = DistributionBlockMessage.toJson(blockMessage);
		jmsTemplate.convertAndSend("block-distribution-queue", jsonMessage);
	}

	@JmsListener(destination = "block-distribution-queue")
	private void asyncDistributeBlock(String blockMessageJson) {
		DistributionBlockMessage blockMessage = DistributionBlockMessage.fromJson(blockMessageJson);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		BlockCreatedMessage message = BlockCreatedMessage.from(HttpAddressService.getNodeAddress(), blockMessage.getData(),
			blockMessage.getHash());
		HttpEntity<BlockCreatedMessage> httpEntity = new HttpEntity<>(message, headers);
		try {
			HandleBlockCreatedResult result = restTemplate.postForObject(HttpAddressService.getCompetitorEndpointAddress(),
				httpEntity,
				HandleBlockCreatedResult.class);
			logger.info("Response from competitor miner is: " + result);
		} catch (ResourceAccessException exception) {
			logger.error("Can not access remote node. Message is: " + exception.getMessage());
		}
	}

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
