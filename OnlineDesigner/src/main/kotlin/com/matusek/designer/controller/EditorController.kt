package com.matusek.designer.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class EditorController {

    @RequestMapping(value = ["/editor"], method = [RequestMethod.GET])
    fun editorPage(): String {
        return "web/editor.html"
    }
}