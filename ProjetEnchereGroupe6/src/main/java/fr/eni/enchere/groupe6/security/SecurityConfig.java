package fr.eni.enchere.groupe6.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private DataSource dataSource ;
	
	private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	
	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.jdbcAuthentication()
			.dataSource( dataSource )
			.usersByUsernameQuery( " SELECT pseudo, mot_de_passe, 1 FROM UTILISATEURS WHERE pseudo = ? " )
			.authoritiesByUsernameQuery(" SELECT ?,'admin'" )
			.passwordEncoder( passwordEncoder )
			;
	}
	
	@Bean
	public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		http
			
			.formLogin(Customizer.withDefaults())
			//.logout(Customizer.withDefaults())
			.logout(logout-> 
			logout 
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").permitAll())
		
			
			
			
			.authorizeHttpRequests( auth -> auth
				/*.requestMatchers( "/encheresMesVentes").authenticated()
				.requestMatchers( "/encheresConnecte").authenticated()
				.requestMatchers( "/monProfil").authenticated()
				.requestMatchers( "/profil").authenticated()
				.requestMatchers( "/encherir").authenticated()
				.requestMatchers( "/nouvelleVente").authenticated()
				.requestMatchers( "/modifierVente").authenticated()
				.requestMatchers( "/inscription" ).permitAll()
				.requestMatchers( "/connexion" ).permitAll()
				.requestMatchers("/encheres").permitAll()
				.requestMatchers("/").permitAll()*/
				.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
					.requestMatchers("/inscription","/","/encheres","/connexion").permitAll()
					.anyRequest().authenticated()
				
				
				
				
			);
		
		return http.build();
		
	
}	 
	
	
	
	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
//		http.authorizeHttpRequests(auth -> {
//			auth 
//				.requestMatchers (HttpMethod.GET,"/encheres").permitAll()
//				.requestMatchers(HttpMethod.GET,"/").permitAll()
//				.requestMatchers(HttpMethod.GET,"/connexion").permitAll()
//				.requestMatchers(HttpMethod.POST,"/connexion").permitAll()
//				.requestMatchers(HttpMethod.POST,"/inscription").permitAll()
//				.requestMatchers(HttpMethod.GET,"/inscription").permitAll()
//				
//				.requestMatchers(HttpMethod.GET,"/encheresConnecte").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.POST,"/encheresMesVentes").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.GET,"/encheresMesVentes").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.GET,"/profil").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.GET,"/monProfil").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.POST,"/monProfil").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.GET,"/modifierVente").hasRole("MEMBRE")
//				.requestMatchers(HttpMethod.GET,"/nouvelleVente").permitAll()
//				.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
//				.anyRequest().authenticated();
//		});
		//formulaire de connexion
		//http.formLogin(Customizer.withDefaults());
		
		
//		http.formLogin(form -> {
//			form.loginPage("/connexion").permitAll();
//			form.defaultSuccessUrl("/encheresConnecte").permitAll();
//		});
		
		
		
		
		
		
	
	
	
	
//	@Bean
//	UserDetailsManager userDetailsManager() {
//		var userDetailService = new InMemoryUserDetailsManager();
//		var user = User.withUsername("admin").password("azerty").roles("MEMBRE").build();
//		var user2 = User.withUsername("bob").password("azertyu").roles("MEMBRE").build();
//		userDetailService.createUser(user);
//		
//		
//		//var user2 = User.withUsername("bob").password("qwerty").roles("MEMBRE").build();
//		//userDetailService.createUser(user2);
//		return userDetailService;
//		
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return NoOpPasswordEncoder.getInstance();
//		//return new BCryptPasswordEncoder();
//	}
	
	
}
