package com.blockchain.miner.domain.blockchain;

import com.blockchain.miner.domain.block.Block;

import java.util.LinkedList;

public class BlockChain {

	private LinkedList<Block> chain;

	public static BlockChain empty() {
		BlockChain blockChain = new BlockChain();
		blockChain.setChain(new LinkedList<>());
		return blockChain;
	}

	private void setChain(LinkedList<Block> chain) {
		this.chain = chain;
	}

	public void addBlock(Block block) {
		if (!chain.isEmpty()) {
			block.addHashOfLastBlock(chain.getLast().getHash());
		}
		chain.add(block);
	}

	@Override
	public String toString() {
		return "BlockChain{" +
			"chain=" + chain +
			'}';
	}
}
