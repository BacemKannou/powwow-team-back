package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dao.UserRepository;
import fr.docapost.powwow.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {
	@Autowired
	UserRepository userRepo;

	User getUserByUserName(String name){
		return userRepo.findByUserName(name);
	}
	

	void saveUser(User user){
		userRepo.save(user);
	}
}
