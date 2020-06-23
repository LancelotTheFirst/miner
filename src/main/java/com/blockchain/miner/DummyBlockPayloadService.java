package com.blockchain.miner;

import org.springframework.stereotype.Component;

import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class DummyBlockPayloadService implements BlockPayloadService {

	private static final int TEXT_LENGTH = 10;

	@Override
	public BlockPayload getPayloadForBlock() {
		Map<String, String> dummyPayloadMap = new HashMap<>();
		int randomTimes = ThreadLocalRandom.current().nextInt(5, 10);
		IntStream
			.range(0, randomTimes)
			.forEach(i -> dummyPayloadMap.put(String.valueOf(i), generateRandomText()));

		return BlockPayload.from(dummyPayloadMap);
	}

	private String generateRandomText() {
		byte[] array = new byte[TEXT_LENGTH]; // length is bounded by textLength
		new Random().nextBytes(array);
		return new String(array, StandardCharsets.UTF_8);
	}

}
