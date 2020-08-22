package com.blockchain.miner.domain.block.hash;

import com.blockchain.miner.domain.block.Block;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.*;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

	private final static Logger logger = LoggerFactory.getLogger(HashTest.class);

	@Test
	void hashCreatesForValidBlock() {
		Block blockMock = Mockito.mock(Block.class);
		Mockito.when(blockMock.getData()).thenReturn(new HashMap<>());

		Hash hash = null;
		try {
			hash = Hash.from(blockMock);
		} catch (HashCalculationException e) {
			Assert.fail("Exception occurred when tried to create hash object");
		}

		Assert.assertNotNull(hash);
		Assert.assertFalse(hash.asString().isEmpty());
	}

	@Test
	void createdHashObjectWithCorrectHashValueForBlock() {
		Map<String, String> dataMap = createBlockPayloadDataMap();
		Block blockMock = Mockito.mock(Block.class);
		Mockito.when(blockMock.getData()).thenReturn(dataMap);

		Hash hash = null;
		try {
			hash = Hash.from(blockMock);
		} catch (HashCalculationException e) {
			Assert.fail("Exception occurred when tried to create hash object");
		}

		Assert.assertNotNull(hash);
		Assert.assertEquals("29c87e324b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954", hash.asString());
	}

	private Map<String, String> createBlockPayloadDataMap() {
		return new HashMap<>() {{
			put("0", "8ddd06d8-118f-47bc-9c3b-ab40d52b2632");
			put("1", "b967c0be-0076-407d-a14d-c9ad8a42afcc");
			put("2", "c1ab914a-2232-407e-b395-40433d55a026");
			put("3", "096aa01a-67be-4f26-ab64-18fa8d3c334b");
			put("4", "050effe6-fd64-4219-8a9f-d3ace7753a16");
		}};
	}

	@Test
	void whenCreateHashFromStringHashValueIsEqualsToThisString() {
		String hashString = "hash";

		Hash hash = Hash.fromEarlierCalculatedHashString(hashString);

		Assert.assertEquals(hashString, hash.asString());
	}

	@Test
	void isDesiredReturnsTrueForReallyDesiredHash() {
		int nZeros = 5;
		String desiredHashString = "00000e324b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954";
		String anotherDesiredHashString = "000000004b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954";
		Hash hash = Hash.fromEarlierCalculatedHashString(desiredHashString);
		Hash anotherHash = Hash.fromEarlierCalculatedHashString(anotherDesiredHashString);

		Assert.assertTrue(hash.isDesired(nZeros));
		Assert.assertTrue(anotherHash.isDesired(nZeros));
	}

	@Test
	void isDesiredReturnsFalseForNotDesiredHash() {
		int nZeros = 5;
		String notDesiredHashString = "e8152e324b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954";
		String anotherNotDesiredHashString = "0000e8154b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954";
		Hash hash = Hash.fromEarlierCalculatedHashString(notDesiredHashString);
		Hash anotherHash = Hash.fromEarlierCalculatedHashString(anotherNotDesiredHashString);

		Assert.assertFalse(hash.isDesired(nZeros));
		Assert.assertFalse(anotherHash.isDesired(nZeros));
	}

	@Test
	void recalculateHashLeadsToRightHash() throws HashCalculationException {
		Map<String, String> dataMap = new HashMap<>();
		Block blockMock = Mockito.mock(Block.class);
		Mockito.when(blockMock.getData()).thenReturn(dataMap);
		Hash hash = Hash.from(blockMock);
		dataMap = createBlockPayloadDataMap();
		Mockito.when(blockMock.getData()).thenReturn(dataMap);

		hash.recalculateHash(blockMock);

		Assert.assertEquals("29c87e324b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954", hash.asString());
	}

	@Test
	void asStringReturnsRightRepresentationOfHash() {
		String hashString = "29c87e324b8fbaa421a82e815244f8ac605f5cd536ae466d4b3863cd1a4d7954";

		Hash hash = Hash.fromEarlierCalculatedHashString(hashString);

		Assert.assertEquals(hashString, hash.asString());
	}

}