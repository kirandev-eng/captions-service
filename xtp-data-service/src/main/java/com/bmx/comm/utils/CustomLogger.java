package com.bmx.comm.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.time.Instant;

import javax.annotation.PostConstruct;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.stereotype.Component;

import com.bmx.comm.XtpDataServiceApplication;

import logging.logger.Logger;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class CustomLogger {
	
	private Logger loggerConfig;
	
	private String version;

	private String appName;

	private String machineName;

	private String machineIP;
	
	@PostConstruct
	public void setLogger() throws Exception {
		loggerConfig = Logger.getInstance(null);
		version = getModel().getVersion();
		appName = getModel().getArtifactId();
		machineName =InetAddress.getLocalHost().getHostName();
		machineIP =InetAddress.getLocalHost().getHostAddress();
	}



	public void debug(String msg, String correlationId, String user ) {
		try {
			loggerConfig.debug(correlationId, appName, version, msg, user, machineName,
					machineIP, String.valueOf(Instant.now().toEpochMilli()));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void error(String msg,Exception error, String correlationId, String user) {

		try {
			loggerConfig.error(correlationId, appName, version, msg,error, user, machineName,
					machineIP, String.valueOf(Instant.now().toEpochMilli()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void info(String msg, String correlationId, String user) {

		try {
			loggerConfig.info(correlationId, appName, version, msg, user, machineName,
					machineIP, String.valueOf(Instant.now().toEpochMilli()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void warn(String msg, String correlationId, String user) {

		try {
			loggerConfig.warn(correlationId, appName, version, msg,user, machineName,
					machineIP, String.valueOf(Instant.now().toEpochMilli()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private Model getModel() throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model;
		try {
			model = reader.read(new FileReader("pom.xml"), true);
		} catch (Exception e) {
			model = reader.read(new InputStreamReader(XtpDataServiceApplication.class
					.getResourceAsStream("/META-INF/maven/com.bmx.comm/xtp-data-service/pom.xml")));
		}
		return model;

	}

}
