package vn.tcx.eoffice.registry;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}admin")
                    .credentialsExpired(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .roles("ADMIN");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.and()
			.requestMatchers().antMatchers("/eureka/**")
			.and()
				.authorizeRequests()
					.antMatchers("/eureka/js/**","/eureka/css/**").permitAll()
					.antMatchers("/eureka/**").hasRole("ADMIN")
					.antMatchers("/management/health").permitAll()
		            .antMatchers("/management/info").permitAll()
		            .antMatchers("/management/**").hasRole("ADMIN")
					.anyRequest().denyAll()
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
	}
}
