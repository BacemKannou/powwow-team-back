package fr.myProject.services;

import fr.myProject.entities.Role;
import fr.myProject.entities.User;

public interface AccountService {
 
	public User saveUser(String userName, String password, String confirmerPassword);
	public Role saveRole(Role role);
	public void addRoleToUser(String role, String userName);
	public User findUserByUserName(String userName);
}
