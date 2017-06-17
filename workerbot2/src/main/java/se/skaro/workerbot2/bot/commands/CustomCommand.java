package se.skaro.workerbot2.bot.commands;

import se.skaro.workerbot2.bot.WorkerBot;

public class CustomCommand extends AbstractCommand {

	@Override
	public void call(WorkerBot bot, String sender, String message, String channel) {
		messageSender.sendMessage(sender, message, channel);
	}

}
