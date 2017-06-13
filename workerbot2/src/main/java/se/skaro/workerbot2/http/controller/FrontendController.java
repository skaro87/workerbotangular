package se.skaro.workerbot2.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.skaro.workerbot2.http.model.UserProfile;
import se.skaro.workerbot2.services.UserProfileService;

@RestController
@RequestMapping("/api")
public class FrontendController {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@RequestMapping(method = RequestMethod.GET, path="/settings/{user}")
	public ResponseEntity<UserProfile> getUserProfileForUser(@PathVariable String user) {
		UserProfile userProfle = userProfileService.getUserProfileForUser(user.toLowerCase());
		if (userProfle != null) {
			return new ResponseEntity<>(userProfle, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
