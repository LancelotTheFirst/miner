package com.blockchain.miner.repository.income;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDataRepository extends MongoRepository<BlockCreatedDocument, String> {

	void deleteByHash(String hash);

}
