package se.skaro.workerbot2.services;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import se.skaro.workerbot2.model.UserProfile;
import se.skaro.workerbot2.model.UserProfileRepository;

@Component
public class UserProfileService {

	@Autowired
	UserProfileRepository userRepo;
	
	@Autowired
	AutomatedMessageTask automatedMessages;
	
	private LoadingCache<String, UserProfile> userCache;

	public UserProfileService() {
		userCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
				.build(new CacheLoader<String, UserProfile>() {
					@Override
					public UserProfile load(String key) {
						UserProfile user = getUserProfileFromRepo(key);
						return user;
					}
				});
	}

	public UserProfile getUserProfileForUser(String user) {
		try {
			return userCache.get(user);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserProfile getUserProfileFromRepo(String user) {
		UserProfile ret = null;
		List<UserProfile> data = userRepo.findByUser(user);
		if (data.isEmpty()) {
			ret = new UserProfile(user);
			userRepo.save(ret);
		} else {
			ret = data.get(0);
		}
		return ret;
	}

	public void updateProfile(UserProfile userProfile) {
		List<UserProfile> data = userRepo.findByUser(userProfile.getUser());
		if (data.isEmpty()) {
			userProfile.getCustomCommands().forEach(command -> {
				command.setUser(userProfile);
			});
			userRepo.save(userProfile);
			userCache.put(userProfile.getUser(), userProfile);
		} else {
			UserProfile savedUser = data.get(0);
			savedUser.updateWithNewData(userProfile);
			userRepo.save(savedUser);
		}
		automatedMessages.getMessages().put(userProfile.getUser(), userProfile.getMessages());

	}

}
