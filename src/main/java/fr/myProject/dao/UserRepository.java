package fr.myProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.myProject.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);

}
