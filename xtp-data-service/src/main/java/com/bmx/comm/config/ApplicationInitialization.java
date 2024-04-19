package com.bmx.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bmx.protobuf.refdata.MetalServiceGrpc;
import com.bmx.protobuf.refdata.MetalServiceGrpc.MetalServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class ApplicationInitialization {
	
	private final ConfigProperties configProps;

	@Bean
	public MetalServiceBlockingStub getMetalServiceGrpcClient() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress(configProps.getMetalGrpcClientHost(),
				configProps.getMetalClientPort())
				.usePlaintext().build();
		return MetalServiceGrpc.newBlockingStub(channel);
	}
	
}
