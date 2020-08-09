package com.blockchain.miner.domain.block.hash;

public class HashCalculationException extends Exception {
	public HashCalculationException(String message, Exception e) {
		super(message, e);
	}
}
