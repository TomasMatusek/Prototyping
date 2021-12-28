package com.matusek.designer.controller

import com.matusek.designer.storage.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController("/file")
class FileController {

    @Autowired
    lateinit var fileService: FileService

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile) {
        fileService.uploadFile(file)
    }
}