package fr.myProject.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4200");
		response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials,"
				+ "authorization");

		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else if (request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		String jwt= request.getHeader(SecurityParams.HEADER_STRING);
		if(jwt==null || !jwt.startsWith(SecurityParams.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();

		DecodedJWT decodedJWT= verifier.verify(jwt.substring(SecurityParams.TOKEN_PREFIX.length()));
		String userName =decodedJWT.getSubject();
		List<String>roles= decodedJWT.getClaims().get("roles").asList(String.class);

		Collection<GrantedAuthority> authorities=new ArrayList<>();

		for(String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		//		roles.forEach(action);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,null, authorities);
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request,response);

	}

}
