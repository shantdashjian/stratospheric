package dev.stratospheric.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .ignoringAntMatchers(
        "/stratospheric-todo-updates/**",
        "/websocket/**"
      )
      .and()
      .oauth2Login()
      .and()
      .authorizeRequests(authorize ->
        authorize
          .mvcMatchers(
            "/",
            "/health",
            "/register",
            "/webjars/**",
            "/styles.css",
            "/favicon.svg"
          )
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .logout()
      .logoutSuccessUrl("/");
  }
}
