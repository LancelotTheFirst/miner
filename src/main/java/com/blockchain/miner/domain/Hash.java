package com.blockchain.miner.domain;

import org.slf4j.*;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class Hash {

	private static final Logger logger = LoggerFactory.getLogger(Hash.class);
	private char[] hexHashChars;

	public static Hash from(Block block) throws HashCalculationException {
		char[] hashChars = calculateHash(block);
		Hash hash = new Hash();
		hash.setHexHashChars(hashChars);
		return hash;
	}

	private static char[] calculateHash(Block block) throws HashCalculationException {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			String message = "Really unexpected error in instantiating MessageDigest to calculate hash sum";
			logger.error(message);
			throw new HashCalculationException(message, e);
		}
		byte[] hashBytes =  digest.digest(
			block.getData().toString().getBytes(StandardCharsets.UTF_8));
		return bytesToHex(hashBytes);
	}

	private static char[] bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString().toCharArray();
	}

	public boolean isDesired(int nZeros) {
		for (int i = 0; i < nZeros; i++) {
			if (hexHashChars[i] != Character.forDigit(0, 10)) {
				return false;
			}
		}
		return true;
	}

	public void recalculateHash(Block block) throws HashCalculationException {
		this.hexHashChars = calculateHash(block);
	}

	public void setHexHashChars(char[] hexHashChars) {
		this.hexHashChars = hexHashChars;
	}

	@Override
	public String toString() {
		return String.valueOf(hexHashChars);
	}
}
