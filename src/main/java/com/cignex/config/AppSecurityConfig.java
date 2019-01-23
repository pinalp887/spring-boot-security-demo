package com.cignex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cignex.service.MyUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * List<UserDetails> userDetails = new ArrayList<>();
	 * userDetails.add(User.withDefaultPasswordEncoder().username("pinal").password(
	 * "1234").roles("USER").build());
	 * userDetails.add(User.withDefaultPasswordEncoder().username("manisha").
	 * password("1234").roles("ADMIN").build()); return new
	 * InMemoryUserDetailsManager(userDetails); }
	 */
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("in authentication manager");
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/*
	 * @Bean public AuthenticationProvider authProvider() {
	 * DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	 * provider.setUserDetailsService(userDetailsService);
	 * provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	 * System.out.println(provider.toString()); return provider; }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("in to the http method---------");
//	http.csrf().disable();
		/*
		 * http.authorizeRequests().antMatchers("/api/*").authenticated().anyRequest().
		 * permitAll().and().formLogin() .permitAll(); hasRole('ROLE_USER')
		 */

		/*
		 * http.authorizeRequests().antMatchers("/",
		 * "/api/admin").hasAuthority("ADMIN").anyRequest().authenticated()
		 * .antMatchers("/api/user").hasAuthority("USER").anyRequest().authenticated().
		 * and().formLogin().and()
		 * .exceptionHandling().accessDeniedPage("/api/acess-denied");
		 */

		/*
		 * http.authorizeRequests().antMatchers("/api/admin/**").hasAuthority("ADMIN").
		 * anyRequest().authenticated()
		 * .antMatchers("/api/user/**").hasAuthority("USER").anyRequest().authenticated(
		 * ).and().formLogin().and()
		 * .exceptionHandling().accessDeniedPage("/api/acess-denied");
		 */
		/*
		 * http.authorizeRequests().antMatchers("/api/admin/**").hasAuthority("ADMIN")
		 * .anyRequest().authenticated() .antMatchers("/api/user/**").access("USER")
		 * .anyRequest().fullyAuthenticated() .and().formLogin() .and()
		 * .exceptionHandling().accessDeniedPage("/api/acess-denied") .and()
		 * .logout().invalidateHttpSession(true)
		 * .clearAuthentication(true).logoutRequestMatcher(new
		 * AntPathRequestMatcher("/api/logout"))
		 * .logoutSuccessUrl("/api/logout-sucess").permitAll();
		 */

		http.authorizeRequests()
		.antMatchers("/api/admin*/**").hasRole("ADMIN")
		.antMatchers("/api/user*/**").hasRole("USER")
		.antMatchers("/").permitAll()
		.and().formLogin()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/api/acess-denied")
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
		.logoutSuccessUrl("/api/logout-sucess").permitAll()
		.and()
		.httpBasic();

		/*
		 * http.authorizeRequests().antMatchers("/","/api/admin/**").access(
		 * "hasRole('ADMIN')").antMatchers("/api/user/**")
		 * .access("hasRole('USER')").and().formLogin().and().exceptionHandling()
		 * .accessDeniedPage("/api/acess-denied");
		 */
	}
}
