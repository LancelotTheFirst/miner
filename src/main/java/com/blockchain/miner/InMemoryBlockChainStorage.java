package com.blockchain.miner;

import org.springframework.stereotype.Component;

@Component
public class InMemoryBlockChainStorage implements BlockChainStorage{

	@Override
	public BlockChain getBlockChain() {
		return BlockChain.empty();
	}
}
