package com.alvaro.udemy.springboot.jpa.demo;

import com.alvaro.udemy.springboot.jpa.demo.auth.handler.LoginSuccessHandler;
import com.alvaro.udemy.springboot.jpa.demo.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	LoginSuccessHandler successHandler;
	@Autowired
	DataSource dataSource;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	JpaUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar**","/local","/api/clientes/listar").permitAll()
				.anyRequest().authenticated().and().formLogin().successHandler(successHandler).loginPage("/login").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/error_403");
		//				.antMatchers("/ver/**","/uploads/**").hasAnyRole("USER")
		//				.antMatchers("/form/**","/eliminar/**","/factura/**").hasAnyRole("ADMIN")
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
		/*build.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select username, password,enabled from users where username = ?")
				.authoritiesByUsernameQuery("Select u.username, a.authority from authorities a, users u where a.user_id =u.id and u.username = ?");
		*/

		/*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		Usuario.UserBuilder users = Usuario.builder().passwordEncoder(encoder::encode);

		build.inMemoryAuthentication()
				.withUser(users
					.username("admin")
					.password("12345")
					.roles("ADMIN","USER"))
				.withUser(users
					.username("alvaro")
					.password("12345")
					.roles("USER"));*/
	}
}
