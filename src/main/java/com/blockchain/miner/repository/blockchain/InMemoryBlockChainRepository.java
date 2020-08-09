package com.blockchain.miner.repository.blockchain;

import com.blockchain.miner.domain.blockchain.BlockChain;
import org.springframework.stereotype.Component;

@Component
public class InMemoryBlockChainRepository implements BlockChainRepository {

	@Override
	public BlockChain getBlockChain() {
		return BlockChain.empty();
	}
}
