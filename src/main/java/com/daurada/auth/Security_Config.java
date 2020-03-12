package com.daurada.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.daurada.dir.DIR;

@Configuration
@EnableWebSecurity
public class Security_Config extends WebSecurityConfigurerAdapter {
	
    @Autowired
    @Qualifier("userDetails_Service")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthProvider authProvider;
    
    @Autowired 
    private LoginSuccesHandler loginSucces;
    
    @Autowired
    private LoginFailureHandler loginFailure;

    @Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
			.antMatchers(DIR.ROOT, DIR.SIGN_UP).permitAll()
			.antMatchers(DIR.HOME).authenticated()
			.antMatchers(DIR.USER + "/**").denyAll()
			.antMatchers(DIR.ADMIN + "/**").hasAuthority(Authority.ADMIN.name())
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginProcessingUrl(DIR.SIGN_IN)
            .successHandler(loginSucces)
		//	.defaultSuccessUrl("/user/home")
            .failureHandler(loginFailure)
			.permitAll()
			.and()
		.logout()
			.logoutUrl(DIR.SIGN_OUT)
			.logoutSuccessHandler(logoutSuccessHandler)
			.and()
			
		.csrf().disable()
		.httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        		.passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(authProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
