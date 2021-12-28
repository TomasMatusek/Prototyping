package com.matusek.designer.config

import com.matusek.designer.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import java.security.SecureRandom
import org.springframework.security.core.userdetails.UserDetailsService



@Configuration
@EnableWebSecurity
class FormLoginSecurityConfig(@Autowired val userService: UserService, @Autowired val passwordEncoder: BCryptPasswordEncoder): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        if (http == null) return
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**", "/libs/**").permitAll()
                .antMatchers("/home", "/login").permitAll()
                .antMatchers("/**").hasRole("USER")
                .and().formLogin().loginPage("/login")
                .and().logout()
                .and().csrf().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth == null) return
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
    }
}