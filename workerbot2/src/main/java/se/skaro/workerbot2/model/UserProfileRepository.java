package se.skaro.workerbot2.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserProfileRepository extends CrudRepository<UserProfile, Long>{
	
	List<UserProfile> findByUser(String user);
	List<UserProfile> findByIgn(String ign);

}
