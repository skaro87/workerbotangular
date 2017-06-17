package se.skaro.workerbot2.bot.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.skaro.workerbot2.bot.WorkerBot;

/**
 * The Class Message. Used to store and send a single message.
 */
public class Message {
	
	private static final Logger logger = LoggerFactory.getLogger(Message.class);

	private WorkerBot bot;
	private String sender;
	private String message;
	private String channel;
	private boolean whisper;

	/**
	 * Instantiates a new message.
	 *
	 * @param bot the bot
	 * @param sender the sender
	 * @param messsage the messsage
	 * @param channel the channel
	 * @param whisper if the message is to be sent as a whisper
	 */
	public Message(WorkerBot bot, String sender, String messsage, String channel, boolean whisper) {
		this.bot = bot;
		this.sender = sender;
		this.message = messsage;
		this.channel = channel;
		this.whisper = whisper;
	}

	/**
	 * Send message.
	 */
	public void sendMessage() {
		
		if (whisper) {
			logger.info("Sending whisper to: "+sender + ", message: "+message);
			bot.sendRawLineViaQueue("PRIVMSG #jtv :/w " + sender + " " + message);
		} else {
			logger.info("Sending message to channel: "+channel+", message: "+message);
			bot.sendMessage(channel, message);
		}
	}

}
