package se.skaro.workerbot2.http.model;

import java.util.List;

public class UserProfile {
	
	private boolean cardCommand;
	private boolean usePrefixCardCommand;
	private String cardCommandPrefix;
	
	private boolean imageCommand;
	
	private boolean hexDataCommands;
	private boolean priceCommand;
	private boolean exhangeRateCommands;
	
	private boolean ignCommand;
	private boolean whoisCommand;
	
	
	private boolean timedMessages;
	private List<String> messages;
	private int messageCooldown;
	
	
	public boolean isCardCommand() {
		return cardCommand;
	}
	public void setCardCommand(boolean cardCommand) {
		this.cardCommand = cardCommand;
	}
	public boolean isUsePrefixCardCommand() {
		return usePrefixCardCommand;
	}
	public void setUsePrefixCardCommand(boolean usePrefixCardCommand) {
		this.usePrefixCardCommand = usePrefixCardCommand;
	}
	public String getCardCommandPrefix() {
		return cardCommandPrefix;
	}
	public void setCardCommandPrefix(String cardCommandPrefix) {
		this.cardCommandPrefix = cardCommandPrefix;
	}
	public boolean isImageCommand() {
		return imageCommand;
	}
	public void setImageCommand(boolean imageCommand) {
		this.imageCommand = imageCommand;
	}
	public boolean isHexDataCommands() {
		return hexDataCommands;
	}
	public void setHexDataCommands(boolean hexDataCommands) {
		this.hexDataCommands = hexDataCommands;
	}
	public boolean isPriceCommand() {
		return priceCommand;
	}
	public void setPriceCommand(boolean priceCommand) {
		this.priceCommand = priceCommand;
	}
	public boolean isExhangeRateCommands() {
		return exhangeRateCommands;
	}
	public void setExhangeRateCommands(boolean exhangeRateCommands) {
		this.exhangeRateCommands = exhangeRateCommands;
	}
	public boolean isIgnCommand() {
		return ignCommand;
	}
	public void setIgnCommand(boolean ignCommand) {
		this.ignCommand = ignCommand;
	}
	public boolean isWhoisCommand() {
		return whoisCommand;
	}
	public void setWhoisCommand(boolean whoisCommand) {
		this.whoisCommand = whoisCommand;
	}
	public boolean isTimedMessages() {
		return timedMessages;
	}
	public void setTimedMessages(boolean timedMessages) {
		this.timedMessages = timedMessages;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public int getMessageCooldown() {
		return messageCooldown;
	}
	public void setMessageCooldown(int messageCooldown) {
		this.messageCooldown = messageCooldown;
	}
	
	
	
	

}
