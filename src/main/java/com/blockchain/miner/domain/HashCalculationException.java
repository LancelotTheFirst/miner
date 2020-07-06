package com.blockchain.miner.domain;

public class HashCalculationException extends Exception {
	public HashCalculationException(String message, Exception e) {
		super(message, e);
	}
}
