package com.blockchain.miner.repository;

import com.blockchain.miner.domain.BlockChain;
import org.springframework.stereotype.Component;

@Component
public class InMemoryBlockChainRepository implements BlockChainRepository {

	@Override
	public BlockChain getBlockChain() {
		return BlockChain.empty();
	}
}
