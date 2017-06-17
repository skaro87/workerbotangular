package se.skaro.workerbot2.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.skaro.workerbot2.bot.messages.MessageSender;

@Component
public class AutomatedMessageTask extends TimerTask {

	@Autowired
	private MessageSender messageSender;

	@Autowired
	private UserProfileService userService;

	private Map<String, List<String>> messages;
	private Map<String, LocalDateTime> lastMessage;

	@PostConstruct
	public void postConstruct() {
		messages = new HashMap<>();
		lastMessage = new HashMap<>();
		userService.userRepo.findAll().forEach(u -> {
			messages.put(u.getUser(), u.getMessages());
		});
	}

	@Override
	public void run() {
		messages.entrySet().forEach(entry -> {
			if (!entry.getValue().isEmpty()) {
				String user = entry.getKey();
				boolean sendMessage = false;
				if (lastMessage.containsKey(user)) {
					if (lastMessage.get(user)
							.isBefore(LocalDateTime.now().minus(
									new Long(userService.getUserProfileForUser(user).getMessageCooldown() - 1),
									ChronoUnit.SECONDS))) {
						sendMessage = true;

					}
				} else {
					sendMessage = true;
				}
				
				if (sendMessage) {
					messageSender.sendMessage(user, getRandom(entry.getValue()), "#"+user);
					lastMessage.put(user, LocalDateTime.now());
				}
			}
			
		});

	}

	String getRandom(List<String> messages) {
		Random rand = new Random();
		return messages.get(rand.nextInt(messages.size()));

	}

	public Map<String, List<String>> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, List<String>> messages) {
		this.messages = messages;
	}

	public Map<String, LocalDateTime> getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Map<String, LocalDateTime> lastMessage) {
		this.lastMessage = lastMessage;
	}
	
	

}
