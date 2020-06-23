package com.blockchain.miner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinerApplication implements CommandLineRunner {

	private MiningEngineApplicationService applicationService;

	public static void main(String[] args) {
		SpringApplication.run(MinerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		applicationService.start();
	}

	@Autowired
	public void setApplicationService(MiningEngineApplicationService applicationService) {
		this.applicationService = applicationService;
	}

}
