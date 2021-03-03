package fr.docapost.powwow.api.security;

import fr.docapost.powwow.api.entities.User;
import fr.docapost.powwow.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserDetailsSeviceImpl implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPwdEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = accountService.findUserByUserName(userName);
		if(user==null) {
			throw new UsernameNotFoundException(userName);
		}
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),bCryptPwdEncoder.encode(user.getPassword()),authorities);
	}

}
