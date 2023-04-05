package com.matusek.designer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class FormLoginSecurityConfig(): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        if (http == null) return
        http.authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/img/**", "/libs/**").permitAll()
            .antMatchers("/home", "/login", "/editor").permitAll()
            .antMatchers("/aaaa").hasRole("USER")
            .and().csrf().disable()
    }
}