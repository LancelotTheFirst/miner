package com.blockchain.miner.infrastructure.distribution;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpAddressService {

	private static String nodeHost;
	private static String nodePort;
	private static String competitorHost;
	private static String competitorMinerPort;
	private static String methodPath;

	public static String getNodeAddress() {
		return createAddress(nodeHost, nodePort);
	}

	public static String getCompetitorEndpointAddress() {
		return createAddress(competitorHost, competitorMinerPort, methodPath);
	}

	private static String createAddress(String host, String port, String methodPath) {
		return createAddress(host, port) + methodPath;
	}

	private static String createAddress(String host, String port) {
		return "http://" + host + ":" + port;
	}

	@Value("${server.host}")
	public void setNodeHost(String nodeHost) {
		HttpAddressService.nodeHost = nodeHost;
	}

	@Value("${server.port}")
	public void setNodePort(String nodePort) {
		HttpAddressService.nodePort = nodePort;
	}

	@Value("${competitor.miner.host}")
	public void setCompetitorHost(String competitorHost) {
		HttpAddressService.competitorHost = competitorHost;
	}

	@Value("${competitor.miner.port}")
	public void setCompetitorMinerPort(String competitorMinerPort) {
		HttpAddressService.competitorMinerPort = competitorMinerPort;
	}

	@Value("${distribute.method.path}")
	public void setMethodPath(String methodPath) {
		HttpAddressService.methodPath = methodPath;
	}

}
