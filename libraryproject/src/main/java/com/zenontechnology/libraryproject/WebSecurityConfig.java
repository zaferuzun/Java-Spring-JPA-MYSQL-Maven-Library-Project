package com.zenontechnology.libraryproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zenontechnology.libraryproject.service.LibraryUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new LibraryUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/contact", "/about", "/register", "/save", "/index", "/js/**", "/css/**", "/Home/**",
						"/images/**", "/HomeUserPage/**", "/UserPanel/**", "/getAuthor2/**", "/orders/**",
						"/plugins/**", "/dist/**", "/build/**", "/ManagerPanel/**", "/usersbook/**")
				.permitAll().antMatchers("/**/edit/**", "/**/manager/**").hasAnyAuthority("ADMIN", "EDITOR")
				.antMatchers("/delete/**", "/manager/**").hasAuthority("ADMIN").anyRequest().authenticated().and()
				.formLogin().permitAll().loginPage("/login").usernameParameter("UserEmail")
				.passwordParameter("UserPassword").loginProcessingUrl("/doLogin").defaultSuccessUrl("/loginSuccess")
				.and().logout().logoutSuccessUrl("/logoutSuccess").permitAll().and().exceptionHandling()
				.accessDeniedPage("/403");
	}
	/*
	 * @Bean public HttpFirewall looseHttpFirewall() { StrictHttpFirewall firewall =
	 * new StrictHttpFirewall(); firewall.setAllowSemicolon(true);
	 * firewall.setAllowUrlEncodedPercent(true);
	 * firewall.setAllowUrlEncodedSlash(true);
	 * firewall.setAllowUrlEncodedPeriod(true); firewall.setAllowBackSlash(true);
	 * return firewall; }
	 * 
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * super.configure(web); web.httpFirewall(looseHttpFirewall()); }
	 */

}
