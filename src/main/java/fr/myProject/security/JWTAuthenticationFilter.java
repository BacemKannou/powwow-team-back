package fr.myProject.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.myProject.entities.User;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user= null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("++++++++++++++");
		System.out.println("username:"+ user.getUserName());
		System.out.println("password:"+ user.getPassword());
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUserName(),user.getPassword() ));
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		org.springframework.security.core.userdetails.User springUser= 
				(org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
//		String jwt = Jwts.builder()
//				.setSubject(springUser.getUsername())
//				.setExpiration(
//						new Date(System.currentTimeMillis()+ SecurityParams.EXPIRATIONTIME))
//				.signWith(SignatureAlgorithm.HS256, SecurityParams.SECRET)
//				.claim("roles", springUser.getAuthorities())
//				.compact();
//		response.addHeader(SecurityParams.HEADER_STRING, SecurityParams.TOKEN_PREFIX+jwt);
		
		List<String> roles = new ArrayList<String>();
		authResult.getAuthorities().forEach(a-> {
		roles.add(a.getAuthority());	
		});
		String jsonWebToken= JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(springUser.getUsername())
				.withArrayClaim("roles",roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+ 10000000000l))
				.sign(Algorithm.HMAC256(SecurityParams.SECRET));
		response.addHeader("Authorization", jsonWebToken);
		
//		super.successfulAuthentication(request, response, chain, authResult);
	}

}
