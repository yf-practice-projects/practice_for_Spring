package com.example;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// 設定内容
		http.formLogin(login -> login.loginProcessingUrl("/login")	// ユーザーIｄ・パスワード送信先URL
				.loginPage("/loginForm")								// ログイン画面URL
				.defaultSuccessUrl("/list")						// ログイン成功後のリダイレクト先URL
				.failureUrl("/loginForm?error")							// ログイン失敗後のリダイレクト先URL
				.usernameParameter("userid")							// spring security上で指定されるデフォルトのhtml上の値はusername。そこをuseridにしている
				.permitAll())											// ログイン画面は未ログインでもアクセス可能とする
		
		.logout(logout -> logout.logoutSuccessUrl("/loginForm")
				.logoutUrl("/logout"))
		.authorizeHttpRequests(authz -> authz
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//			.requestMatchers(new AntPathRequestMatcher("/list")).hasRole("USER")
			.requestMatchers(new AntPathRequestMatcher("/signUp")).permitAll()	// "/signUp"はログインなしでもアクセス可能にする
			.anyRequest().authenticated()										// 他のURLはログイン後のみアクセス可能
		)
		.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.sessionFixation().newSession())
		
		;
		return http.build();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}