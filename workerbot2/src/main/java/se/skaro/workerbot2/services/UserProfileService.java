package se.skaro.workerbot2.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import se.skaro.workerbot2.http.model.UserProfile;

@Component
public class UserProfileService {
	
	private Map<String, UserProfile> userProfiles;
	
	public UserProfileService() {
		UserProfile up = new UserProfile();
		up.setCardCommand(true);
		up.setUsePrefixCardCommand(true);
		up.setCardCommandPrefix("card");
		
		up.setExhangeRateCommands(true);
		up.setHexDataCommands(true);
		up.setIgnCommand(true);
		up.setImageCommand(true);
		up.setPriceCommand(true);
		up.setWhoisCommand(true);
		
		up.setTimedMessages(true);
		up.setMessageCooldown(10);
		List<String> timedMessages = new ArrayList<>();
		timedMessages.add("Test message 1");
		timedMessages.add("Test message 2");
		timedMessages.add("Test message 3");
		up.setMessages(timedMessages);
		userProfiles = new HashMap<>();
		userProfiles.put("skaro87", up);
		
	}
	
	public UserProfile getUserProfileForUser(String user) {
		return userProfiles.get(user);
	}
	
	

}
