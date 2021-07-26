package fr.docapost.powwow.api.security;

import fr.docapost.powwow.api.dao.UserRepository;
import fr.docapost.powwow.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityInfo {

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser(){
        String currentUserName =null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        currentUserName = authentication.getName();
        if(currentUserName!=null){
            return userRepository.findByUserName(currentUserName);
        }else {
            return null;
        }

    }
}
