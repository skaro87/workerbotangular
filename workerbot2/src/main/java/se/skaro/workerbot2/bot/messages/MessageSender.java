package se.skaro.workerbot2.bot.messages;

import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.skaro.workerbot2.bot.WorkerBot;
import se.skaro.workerbot2.util.ExternalConfigComponents;

/**
 * The Class MessageSender. Used to send messages with the ChatBot
 */
@Component
public class MessageSender {

	@Autowired
	private WorkerBot bot;

	/** The config. */
	@Autowired
	private ExternalConfigComponents config;

	private MessageTask messageTask;

	private Timer time;

	@PostConstruct
	public void postConstruct() {
		time = new Timer();
		messageTask = new MessageTask();
		time.schedule(messageTask, 0, config.getMillisecondsBetweenMessages());
	}

	public void sendMessage(String sender, String message, String channel) {
		sendMessageOrAddToQueue(new Message(bot, sender, message, channel, false));
	}

	public void sendMessageOrWhisper(String sender, String message, String channel) {

		// TODO: Implement
		sendMessageOrAddToQueue(new Message(bot, sender, message, channel, false));

	}

	public void sendWhisper(String sender, String message, String channel) {
		sendMessageOrAddToQueue(new Message(bot, sender, message, channel, true));
	}

	private void sendMessageOrAddToQueue(Message message) {
		messageTask.getMessageQueue().add(message);

	}

}
