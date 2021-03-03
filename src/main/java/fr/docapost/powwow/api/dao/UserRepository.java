package fr.docapost.powwow.api.dao;

import fr.docapost.powwow.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);

}
