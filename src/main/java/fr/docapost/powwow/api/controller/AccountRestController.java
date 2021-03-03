package fr.docapost.powwow.api.controller;

import fr.docapost.powwow.api.entities.User;
import fr.docapost.powwow.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountRestController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRePassword())){
			throw new RuntimeException("vous devez confirmer votre password !!");
		}
		User user= accountService.findUserByUserName(userForm.getUserName());
		if(user!=null)
			throw new RuntimeException("cet utilisateur déjà existe !!");
			User userA= new User();
			userA.setUserName(userForm.getUserName());
			userA.setPassword(userForm.getPassword());
//			accountService.addRoleToUser("USER", user.getUserName());
			return accountService.saveUser(userForm.getUserName(),userForm.getPassword(), userForm.getRePassword());
		
	
	}
}
