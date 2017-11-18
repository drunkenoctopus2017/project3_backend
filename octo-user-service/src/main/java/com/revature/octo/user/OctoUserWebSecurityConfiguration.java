package com.revature.octo.user;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class OctoUserWebSecurityConfiguration {//extends WebSecurityConfigurerAdapter {
	/*@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;*/


/*	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configureGlobal");
		auth.userDetailsService(userDetailsService);*/ // pulls the password &
														// username from DB
//		 auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		// .passwordEncoder(new BCryptPasswordEncoder());
	//}
/*
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow these urls
		web.ignoring().antMatchers("/");
	}
*/
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.headers().disable().csrf().disable().authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/dashboard**").authenticated().and()

				// .hasRole("ADMIN").and()
				.formLogin()
					.loginPage("/")
					.loginProcessingUrl("/authenticate")
						.successHandler(loginSuccessHandler())
						.failureHandler(loginFailureHandler())
							.usernameParameter("username")
							.passwordParameter("password")
				.permitAll()
					.and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll();

	}

	public AuthenticationSuccessHandler loginSuccessHandler() {
		return (request, response, authentication) -> {
			System.out.println("success login");
			response.sendRedirect("/dashboard");
		};
	}

	public AuthenticationFailureHandler loginFailureHandler() {
		return (request, response, exception) -> {
			// request.getSession().setAttribute("flash", new
			// FlashMessage("Incorrect username and/or password. Please try
			// again.", FlashMessage.Status.FAILURE));
			System.out.println("FAILED Login ");
			response.sendRedirect("/");
		};
	}*/
}
