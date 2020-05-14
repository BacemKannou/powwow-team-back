package fr.myProject.services;

import org.springframework.beans.factory.annotation.Autowired;

import fr.myProject.dao.UserRepository;
import fr.myProject.entities.User;

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
