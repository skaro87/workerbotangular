package se.skaro.workerbot2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	
	private boolean cardCommand;
	private boolean usePrefixCardCommand;
	private String cardCommandPrefix;
	private boolean imageCommand;
	private boolean hexDataCommands;
	private boolean priceCommand;
	private boolean exhangeRateCommands;
	private boolean ignCommand;
	private boolean timedMessages;
	private boolean inChannel;
	
	@ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
	private List<String> messages;
	private int messageCooldown;
	private String user;
	private String ign;
	private boolean whispers;
	
	@OneToMany(mappedBy="user", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Command> customCommands;
	
	public UserProfile() {
	}
	
	public UserProfile(String user) {
		cardCommand = true;
		cardCommandPrefix = "card";
		exhangeRateCommands = true;
		hexDataCommands = true;
		ignCommand = true;
		imageCommand = true;
		messageCooldown = 30;
		messages = new ArrayList<String>();
		priceCommand = true;
		timedMessages = false;
		usePrefixCardCommand = true;
		whispers = false;
		setInChannel(false);
		this.user = user;
		this.customCommands = new ArrayList<Command>();
	}
	
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
	public boolean isTimedMessages() {
		return timedMessages;
	}
	public void setTimedMessages(boolean timedMessages) {
		this.timedMessages = timedMessages;
	}
	public List<String> getMessages() {
		if (messages == null){
			messages = new ArrayList<>();
		}
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getIgn() {
		return ign;
	}
	public void setIgn(String ign) {
		this.ign = ign;
	}

	public boolean isWhispers() {
		return whispers;
	}
	public void setWhispers(boolean whispers) {
		this.whispers = whispers;
	}
	public boolean isInChannel() {
		return inChannel;
	}

	public void setInChannel(boolean inChannel) {
		this.inChannel = inChannel;
	}
	
	public List<Command> getCustomCommands() {
		return customCommands;
	}

	public void setCustomCommands(ArrayList<Command> customCommands) {
		this.customCommands = customCommands;
	}

	public void updateWithNewData(UserProfile userProfile) {
		cardCommand = userProfile.isCardCommand();
		usePrefixCardCommand = userProfile.isUsePrefixCardCommand();
		cardCommandPrefix = userProfile.getCardCommandPrefix();
		imageCommand = userProfile.isImageCommand();
		hexDataCommands = userProfile.isHexDataCommands();
		priceCommand = userProfile.isPriceCommand();
		exhangeRateCommands = userProfile.isUsePrefixCardCommand();
		ignCommand = userProfile.isIgnCommand();
		timedMessages = userProfile.isTimedMessages();
		messages = userProfile.getMessages();
		messageCooldown = userProfile.getMessageCooldown();
		ign = userProfile.getIgn();
		whispers = userProfile.isWhispers();
		userProfile.getCustomCommands().forEach(command ->{
			command.setUser(userProfile);
		});
		customCommands = userProfile.getCustomCommands();
		
	}
	

}
