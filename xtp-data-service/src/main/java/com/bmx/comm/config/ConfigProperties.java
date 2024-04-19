package com.bmx.comm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ConfigProperties {
	
	@Value("${metal.grpc.client.host}")
	private String metalGrpcClientHost;

	@Value("${metal.grpc.client.port}")
	private int metalClientPort;

}
