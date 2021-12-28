package com.matusek.designer.user.controller

import com.matusek.designer.security.SecurityService
import com.matusek.designer.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserController {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var securityService: SecurityService

    @GetMapping("/login")
    fun loginPage(): String {
        return "web/login.html"
    }

    @PostMapping("/login")
    fun login(
            @RequestParam("username", defaultValue = "") username: String,
            @RequestParam("password", defaultValue = "") password: String,
            model: Model): String
    {

        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            model.addAttribute("message", "Please fill inputs")
            model.addAttribute("success", false)
            return "redirect:/login"
        }

        try {
            val result = securityService.authenticate(UsernamePasswordAuthenticationToken(username, password))
            if (result.isAuthenticated) {
                model.addAttribute("message", "Sucesfully authenticated")
                model.addAttribute("success", true)
            } else {
                model.addAttribute("message", "Authentication failed")
                model.addAttribute("success", false)
            }
        } catch (e: AuthenticationException) {
            model.addAttribute("message", "Invalid credentials")
            model.addAttribute("success", false)
        }

        return "redirect:login"
    }

    @PostMapping("/register")
    fun register(@RequestParam("email") email: String, @RequestParam("password") password: String, @RequestParam("passwordRepeat") passwordRepeat: String): ResponseEntity<Unit> {
        userService.register(email, password, passwordRepeat)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("/logout")
    fun register(): ResponseEntity<Unit> {
        // securityService.logout()
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}