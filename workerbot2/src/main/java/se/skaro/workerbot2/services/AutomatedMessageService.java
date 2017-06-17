package se.skaro.workerbot2.services;

import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutomatedMessageService {

	@Autowired
	private AutomatedMessageTask messageTask;

	private Timer time;

	@PostConstruct
	public void postConstruct() {

		time = new Timer();
		time.schedule(messageTask, 0, 5000);

	}

}
