/* 
* @Author: Gowri Gary Shankar
* @Date:   2014-04-19 12:45:13
* @Last Modified by:   Gowri Gary Shankar
* @Last Modified time: 2014-04-19 12:51:20
*/
package com.prodapt.m2m.na.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.prodapt.m2m.rest.domain.Application;
import com.prodapt.m2m.rest.domain.Command;
import com.prodapt.m2m.rest.domain.Container;


@Component
public class NetworkApp {

	@Autowired
    RestTemplate restTemplate;

	private String appId;
	private String deviceId;
	private String m2mPoC;

	public Application createApplication() {

		Application newApp = new Application();
		
		return newApp;

	}

	public Container createDevice() {

		Container device = new Container();
		
		HttpEntity<Container> deviceEntity = new HttpEntity<Container>(device);
			
		String deviceLocationAtm2mPoC = m2mPoC + "/pronet/applications/" + 
			appId + "/containers";
			
		ResponseEntity<Container> newDeviceEntity = restTemplate.postForEntity(
			deviceLocationAtm2mPoC, deviceEntity, Container.class);
			
		Container newDevice = newDeviceEntity.getBody();
		String deviceId = newDevice.getContainerId();

		this.deviceId = deviceId;

		System.out.println("Device Id: " + deviceId);
		
		return newDevice;

	}

	public Command sendDeviceCommand() {

		Command command = new Command(1927);

		HttpEntity<Command> commandEntity
			= new HttpEntity<Command> (command);

		String commandAtm2mPoC = m2mPoC + "/pronet/applications/" + 
			appId + "/containers/" + deviceId + "/commands";
	
		ResponseEntity<Command> newCommandEntity = 
			restTemplate.postForEntity(commandAtm2mPoC, commandEntity, 
				Command.class);
	
		Command newCommand = newCommandEntity.getBody();

		System.out.println("Device Id: " + deviceId + " Reading Reference: " + 
			newCommand.getCommandId());
		
		return newCommand;

	}
}