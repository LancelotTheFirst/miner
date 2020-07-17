package com.blockchain.miner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDataRepository extends MongoRepository<BlockCreatedEntity, String> {

	void deleteByHash(String hash);

}
