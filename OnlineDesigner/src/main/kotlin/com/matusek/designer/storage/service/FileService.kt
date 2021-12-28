package com.matusek.designer.storage.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.annotation.security.RolesAllowed

@Service
class FileService {


    @Autowired lateinit var filePath: FilePath
    @Value("\${upload.path}") private lateinit var uploadFolderPath: String
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    fun uploadFile(file: MultipartFile) {

        print("Uploading, user registered")

        // validate file
        // write to DV
        // store

        SecurityContextHolder.getContext();

        // Create user upload folder if not exists
//        if (doesUploadFolderExists()) {
//
//        }

//        logger.info(String.format("File %s uploaded", file.originalFilename))
//        val filePath = FilePath().getUserUploadFolderPath() getUploadFolderFilePath(file.originalFilename)
//        file.transferTo(Paths.get(filePath))
//
//        logger.info(String.format("File %s uploaded", filePath))
    }

    private fun doesUploadFolderExists(userId: String): Boolean {
        val userFolder = filePath.getUserUploadFolderPath(userId);
        return userFolder.exists()
    }

    private fun createUploadFolder(userId: String): Boolean {
        val userFolder = filePath.getUserUploadFolderPath(userId)
        return userFolder.mkdir()
    }
}