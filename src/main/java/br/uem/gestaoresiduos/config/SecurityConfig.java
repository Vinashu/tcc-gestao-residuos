package br.uem.gestaoresiduos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import br.uem.gestaoresiduos.security.AjaxAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		
		registry.userDetailsService(customUserDetailsService);
    }


	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  
	    http
	    .csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/public/js/login**","/public/login","/public/login**","/public/register","/public/logout").permitAll()
	        .antMatchers("/admin","/admin/**").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	    .formLogin()
	        .loginPage("/public/login")
	        .loginProcessingUrl("/public/login")
	        .failureUrl("/public/login?error")
	        .loginProcessingUrl("/authenticate")
	        .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
	        .and()
            .httpBasic()
            .and()
            .logout()
            .logoutUrl("/public/logout")
            .logoutSuccessUrl("/public/login.html")
            .permitAll();
	  }
}
