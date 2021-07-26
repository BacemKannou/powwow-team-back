package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.dao.RoleRepository;
import fr.docapost.powwow.api.dao.UserRepository;
import fr.docapost.powwow.api.entities.Auditable;
import fr.docapost.powwow.api.entities.Role;
import fr.docapost.powwow.api.entities.User;
import fr.docapost.powwow.api.security.SecurityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SecurityInfo securityInfo;
	
	@Override
	public User saveUser(String userName, String password, String confirmerPassword) {
		User user=userRepository.findByUserName(userName);
		if(user != null) {
			throw new RuntimeException("this user already exist !! ");
		}
		
		if(!password.equals(confirmerPassword)){
			throw new RuntimeException("Please confirm your password");
		}
		User newUser = new User();
		newUser.setPassword(password);
		newUser.setUserName(userName);
		newUser.setActived(true);
		userRepository.save(newUser);
		addRoleToUser("USER", userName);
		return newUser;
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String roleName, String userName) {
		User user= userRepository.findByUserName(userName);
		Role role = roleRepository.findByRoleName(roleName);
		user.getRoles().add(role);
		
		
	}

	@Override
	public User findUserByUserName(String userName) {	
		return userRepository.findByUserName(userName);
	}

}
