package fr.eni.enchere.groupe6.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.annotation.security.PermitAll;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth 
				.requestMatchers (HttpMethod.GET,"/encheres").permitAll()
				.requestMatchers(HttpMethod.GET,"/").permitAll()
				.requestMatchers(HttpMethod.GET,"/connexion").permitAll()
				.requestMatchers(HttpMethod.GET,"/inscription").permitAll()
				.requestMatchers(HttpMethod.GET,"/encheresConnecte").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/encheresMesVentes").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/profil").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/monProfil").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/modifierVente").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/nouvelleVente").hasRole("MEMBRE")
				.requestMatchers(HttpMethod.GET,"/nouvelleVente").hasRole("MEMBRE")
				.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
				.anyRequest().authenticated();
		});
		//formulaire de connexion
		http.formLogin(Customizer.withDefaults());
		
		
//		http.formLogin(form -> {
//			form.loginPage("/connexion").permitAll();
//			form.defaultSuccessUrl("/encheresConnecte").permitAll();
//		});
		
		
		
		
		http.logout(logout-> 
			logout 
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").permitAll());
		
		return http.build();
	}
		
		
	
	
	
	@Bean
	UserDetailsManager userDetailsManager() {
		var userDetailService = new InMemoryUserDetailsManager();
		var user = User.withUsername("admin").password("azerty").roles("MEMBRE").build();
		userDetailService.createUser(user);
		
		
		//var user2 = User.withUsername("bob").password("qwerty").roles("MEMBRE").build();
		//userDetailService.createUser(user2);
		return userDetailService;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
		//return new BCryptPasswordEncoder();
	}
	
	
}
