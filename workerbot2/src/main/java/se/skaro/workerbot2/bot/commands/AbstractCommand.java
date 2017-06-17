package se.skaro.workerbot2.bot.commands;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import se.skaro.workerbot2.bot.WorkerBot;
import se.skaro.workerbot2.bot.messages.MessageSender;
import se.skaro.workerbot2.util.ExternalConfigComponents;

/**
 * The Class AbstractCommand. Used as a base for all commands. 
 */
public abstract class AbstractCommand {

	/** The Constant MINIMUM_SEARCH_LENGTH. */
	protected static final int MINIMUM_SEARCH_LENGTH = 3;

	/** The config. */
	@Autowired
	protected ExternalConfigComponents config;

	/** The message sender. */
	@Autowired
	protected MessageSender messageSender;

	/**
	 * Call.
	 *
	 * @param bot the bot used to send the reply
	 * @param sender the sender of the message
	 * @param message the message itself
	 * @param channel the channel where the message was sent
	 */
	public abstract void call(WorkerBot bot, String sender, String message, String channel);

	/**
	 * Gets the message without command.
	 *
	 * @param commandSyntax the command syntax
	 * @param message the message
	 * @return the message without command
	 */
	protected String getMessageWithoutCommand(String commandSyntax, String message) {
		return message.replaceFirst("(?i)" + Pattern.quote(commandSyntax), "");
	}

	/**
	 * Fix white spaces.
	 *
	 * @param message the message
	 * @return the string
	 */
	protected String fixWhiteSpaces(String message) {
		return message.trim().replaceAll("\\s+", " ");
	}

	/**
	 * Checks if the input length is okay.
	 *
	 * @param input the input
	 * @param bot the bot
	 * @param sender the sender
	 * @param channel the channel
	 * @return true, if the input length okay is. Otherwise sends a message about it.
	 */
	protected boolean isInputLengthOkay(String input, WorkerBot bot, String sender, String channel) {

		if (input.length() < MINIMUM_SEARCH_LENGTH) {
			messageSender.sendMessageOrWhisper(sender,
					"You need at least " + MINIMUM_SEARCH_LENGTH + " characters to do a search", channel);
			return false;
		}
		return true;
	}

}
