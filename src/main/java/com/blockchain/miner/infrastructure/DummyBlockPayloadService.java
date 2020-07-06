package com.blockchain.miner.infrastructure;

import com.blockchain.miner.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class DummyBlockPayloadService implements BlockPayloadService {

	@Override
	public BlockPayload getPayloadForBlock() {
		Map<String, String> dummyPayloadMap = new HashMap<>();
		int randomTimes = ThreadLocalRandom.current().nextInt(5, 10);
		IntStream
			.range(0, randomTimes)
			.forEach(i -> dummyPayloadMap.put(String.valueOf(i), UUID.randomUUID().toString()));

		return BlockPayload.from(dummyPayloadMap);
	}

}