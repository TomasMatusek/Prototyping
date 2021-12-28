package com.matusek.designer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
@EntityScan
class DesignerApplication
    fun main(args: Array<String>) {
        runApplication<DesignerApplication>(*args)
    }