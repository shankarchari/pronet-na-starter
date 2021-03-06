/* 
* @Author: Gowri Gary Shankar
* @Date:   2014-04-19 09:34:03
* @Last Modified by:   Gowri Gary Shankar
* @Last Modified time: 2014-04-24 11:32:33
*/

package com.prodapt.m2m.na.events;

import org.springframework.stereotype.Service;

import com.prodapt.m2m.na.core.ContentInstanceInfo;

import reactor.event.Event;
import reactor.function.Consumer;


@Service
public class Receiver implements Consumer<Event<ContentInstanceInfo>> {

	
	public void accept(Event<ContentInstanceInfo> ev) {
		
		ContentInstanceInfo contentInstanceInfo = ev.getData();
		
		System.out.println("Device Params Received.......");
		
		System.out.println("Content Received : " + contentInstanceInfo.getContentInstance().getContent());

		System.out.println("App Id: " + contentInstanceInfo.getAppId());
		System.out.println("Device Id: " + contentInstanceInfo.getDeviceId());
		System.out.println("Time of Issue: " + contentInstanceInfo.getContentInstance().getCreationTime());
		System.out.println("Recieved Time: " + contentInstanceInfo.getReceivedTime());
		
		
		System.out.println("-----------------------------");
		System.out.println();
		
	}
}