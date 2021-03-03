package fr.docapost.powwow.api.services;

import fr.docapost.powwow.api.entities.Role;
import fr.docapost.powwow.api.entities.User;

public interface AccountService {
 
	public User saveUser(String userName, String password, String confirmerPassword);
	public Role saveRole(Role role);
	public void addRoleToUser(String role, String userName);
	public User findUserByUserName(String userName);
}
