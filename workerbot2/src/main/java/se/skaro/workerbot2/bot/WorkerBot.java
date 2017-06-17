package se.skaro.workerbot2.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.skaro.workerbot2.model.UserProfile;
import se.skaro.workerbot2.model.UserProfileRepository;
import se.skaro.workerbot2.services.CommandService;
import se.skaro.workerbot2.services.UserProfileService;
import se.skaro.workerbot2.util.ExternalConfigComponents;

@Component
public class WorkerBot extends PircBot{
	
	private static final Logger logger = LoggerFactory.getLogger(WorkerBot.class);

	/** The config. */
	@Autowired
	private ExternalConfigComponents config;

	/** The commands. */

	/** The user repository. */
	@Autowired
	private UserProfileRepository userRepository;
	
	@Autowired
	private UserProfileService userService;
	
	@Autowired
	private CommandService commandService;

	/**
	 * Post construct. Used to start the bot after the class has been initialized by Spring.
	 */
	@PostConstruct
	public void postConstruct() {
		this.setVerbose(config.isBotVerbiose());
		this.setName(config.getName());
		this.setLogin(config.getName());

		try {
			this.connect(config.getServer(), config.getPort(), config.getOauth());
		} catch (IOException | IrcException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.jibble.pircbot.PircBot#onConnect()
	 */
	@Override
	protected void onConnect() {
		
		joinChannel(config.getChannelPrefix()+ config.getName());
		
		
		if (config.getJoinDBChannels()){
		ArrayList<UserProfile> users = (ArrayList<UserProfile>) userRepository.findAll();
		users.forEach(user -> {

			if (user.isInChannel()) {
				joinChannel(config.getChannelPrefix() + user.getUser());
				sleepForChannelJoin();
			}
		
		});
		}
		
		else {
			Arrays.asList(config.getDefaultChannels()).forEach(user -> {
				joinChannel(config.getChannelPrefix() + user);
				sleepForChannelJoin();
			});
		}
		
		logger.info("Bot started. Channels Joined: "+this.getChannels().length);

		super.onConnect();
	}
	
	@Override
	protected void onDisconnect(){
		logger.error("Bot disconnected. Trying to reconnect");
		postConstruct();
	}

	/**
	 * Sleep for channel join. Used to get under the limit for joining channels on twitch.
	 */
	private void sleepForChannelJoin() {
		
		try {
			Thread.sleep(config.getChannelJoinTimeout());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.jibble.pircbot.PircBot#onMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (message.startsWith(config.getMessagePrefix())){
			UserProfile user = userService.getUserProfileForUser(channel.replace(config.getChannelPrefix(), ""));
			String commandName = message.split(" ")[0].replace(config.getMessagePrefix(), "");
			
			user.getCustomCommands().forEach(customCommand -> {
				if (customCommand.getPrefix().equalsIgnoreCase(commandName)){
					commandService.getCommand("custom").call(this, sender, message, channel);
				}
			});
		}
		
	}

}
