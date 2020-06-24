package com.blockchain.miner;

public class HashCalculationException extends Throwable {
	public HashCalculationException(String message, Exception e) {
		super(message, e);
	}
}
