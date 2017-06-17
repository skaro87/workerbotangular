package se.skaro.workerbot2.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class ExternalConfigComponents. Used to get properties from application.properties
 */
@Component
public class ExternalConfigComponents {

	@Value("${bot.verbiose}")
	private boolean botVerbiose;

	@Value("${twitch.oauth}")
	private String oauth;

	@Value("${twitch.name}")
	private String name;
	
	@Value("${twitch.server}")
	private String server;
	
	@Value("${twitch.port}")
	private int port;
	
	@Value("${twitch.messageprefix}")
	private String messagePrefix;
	
	@Value("${twitch.channelprefix}")
	private String channelPrefix;

	@Value("${twitch.channeljointimeout}")
	private long channelJoinTimeout;
	
	@Value("${twitch.joinmessage}")
	private String joinMessage;
	
	@Value("${twitch.leavemessage}")
	private String leaveMessage;
	
	@Value("${twitch.millisecondsbetweenmessages}")
	private int millisecondsBetweenMessages;
	
	@Value("${twitch.messagebuffer}")
	private int messagebuffer;

	@Value("${twitch.streamdelay}")
	private int streamdelay;
	
	@Value("${twitch.globalwhispersettings}")
	private boolean globalWhisperSettings;
	
	@Value("${twitchapi.clientid}")
	private String twitchClientId;
	
	@Value("${twitchapi.chattersurl}")
	private String twitchApiUrl;
	
	@Value("${twitchapi.gameurl}")
	private String twitchGameUrl;
	
	@Value("${price.ratioitem}")
	private String ratioItem;
	
	@Value("${price.ratioaprox}")
	private Double ratioApprox;
	
	@Value("${img.host}")
	private String imgHostUrl;
	
	@Value("${img.plugin}")
	private String imgPluginUrl;
	
	@Value("${api.keyvalue}")
	private String keyValue;
	
	@Value("${img.width}")
	private int width;
	
	@Value("${img.height}")
	private int height;
	
	@Value("${bot.joindbchannels}")
	private Boolean joinDBChannels;
	
	@Value("${bot.defaultchannels}")
	private String defaultChannels;
	
	public boolean isBotVerbiose() {
		return botVerbiose;
	}

	public String getOauth() {
		return oauth;
	}

	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}

	public int getPort() {
		return port;
	}

	public String getMessagePrefix() {
		return messagePrefix;
	}
	
	public String getChannelPrefix() {
		return channelPrefix;
	}

	public long getChannelJoinTimeout() {
		return channelJoinTimeout;
	}

	public String getJoinMessage() {
		return joinMessage;
	}
	
	public String getLeaveMessage() {
		return leaveMessage;
	}

	public int getMillisecondsBetweenMessages() {
		return millisecondsBetweenMessages;
	}
	
	public boolean getGlobalWhisperSettings() {
		return globalWhisperSettings;
	}

	public int getMessagebuffer() {
		return messagebuffer;
	}

	public String getTwitchApiUrl() {
		return twitchApiUrl;
	}
	
	public String getRatioItem() {
		return ratioItem;
	}

	public double getRatioApprox() {
		return ratioApprox;
	}

	public String getImgHostUrl() {
		return imgHostUrl;
	}

	public String getImgPluginUrl() {
		return imgPluginUrl;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public String getTwitchClientId() {
		return twitchClientId;
	}

	public String getTwitchGameUrl() {
		return twitchGameUrl;
	}

	public int getStreamdelay() {
		return streamdelay;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Boolean getJoinDBChannels() {
		return joinDBChannels;
	}

	public String[] getDefaultChannels() {
		return defaultChannels.split(",");
	}
	
	
	

}

