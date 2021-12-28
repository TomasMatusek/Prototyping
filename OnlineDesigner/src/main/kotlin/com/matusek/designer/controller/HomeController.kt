package com.matusek.designer.controller

import com.matusek.designer.product.domain.Product
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/","/home")
    fun homePage(model: Model): String {
        val products = listOf(Product(1, "Calendar"), Product(2, "Fotokniha"))
        model.addAttribute("products", products)
        return "web/home.html"
    }
}