package se.skaro.workerbot2.services;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import se.skaro.workerbot2.bot.commands.AbstractCommand;
import se.skaro.workerbot2.bot.commands.CustomCommand;

@Component
public class CommandService {
	
	private Map<String, AbstractCommand> commands;
	
	@PostConstruct
	public void postConstruct() {
		commands = new HashMap<>();
		commands.put("custom", new CustomCommand());
	}
	
	public AbstractCommand getCommand(String command){
		return commands.get(command.toLowerCase());
	}

}
