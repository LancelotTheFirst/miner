package com.blockchain.miner;

import org.slf4j.*;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class Hash {

	private static final Logger logger = LoggerFactory.getLogger(Hash.class);
	private byte[] hash;

	public static Hash from(Block block) throws HashCalculationException {
		byte[] hashBytes = calculateHash(block);
		Hash hash = new Hash();
		hash.setHash(hashBytes);
		return hash;
	}

	private static byte[] calculateHash(Block block) throws HashCalculationException {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			String message = "Really unexpected error in instantiating MessageDigest to calculate hash sum";
			logger.error(message);
			throw new HashCalculationException(message, e);
		}
		return digest.digest(
			block.getData().toString().getBytes(StandardCharsets.UTF_8));
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public byte[] getHash() {
		return hash;
	}

	public boolean isDesired(int nZeros) {
		for (int i = 0; i < nZeros; i++) {
			if (hash[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public void recalculateHash(Block block) throws HashCalculationException {
		this.hash = calculateHash(block);
	}

}
