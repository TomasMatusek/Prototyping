package com.matusek.designer.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @RequestMapping(value = ["/products/{id}"], method = [RequestMethod.GET])
    fun getJsonConfiguration(@PathVariable id: String): String {
        return "";
    }
}
