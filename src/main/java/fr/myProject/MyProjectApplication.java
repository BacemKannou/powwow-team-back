package fr.myProject;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.myProject.dao.CategoryRepository;
import fr.myProject.dao.ProductRepository;
import fr.myProject.entities.Category;
import fr.myProject.entities.Role;
import fr.myProject.services.AccountService;
import fr.myProject.services.CategoryService;
import fr.myProject.services.ProductService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Bacem
 *
 */
@SpringBootApplication
public class MyProjectApplication {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args->{
			Category c1 = new Category();
			c1.setId(1l);
			
			Category c2 = new Category();
			c2.setId(2l);
			Stream.of("Ordinateurs", "Imprimantes").forEach(c->{
				if(c.equalsIgnoreCase("Ordinateurs")) {
					c1.setName(c);
					categoryRepository.save(c1);
				}
				if(c.equalsIgnoreCase("Imprimantes")) {
					c2.setName(c);
					categoryRepository.save(c2);
				}
				
				
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			accountService.saveRole(new Role("USER"));
			accountService.saveRole(new Role("ADMIN"));
			
			Stream.of("user1", "user2","admin").forEach(un -> {
				accountService.saveUser(un, "1234", "1234");
				});
			
			Stream.of("product1", "product2","product3").forEach(p -> {
				productService.saveProductWithCategory(Long.parseLong("1"), p);
				});
			
				accountService.addRoleToUser("USER", "user1");
				accountService.addRoleToUser("USER", "user2");
				accountService.addRoleToUser("ADMIN", "admin");
		};
		
	}
	
	@Bean
	 static BCryptPasswordEncoder getBCEncoder(){
		return new BCryptPasswordEncoder();
		
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
