package com.ag.generalsystemsapi.api.helpers

import com.ag.generalsystemsapi.api.model.FileDetailsModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*


@Component
class FileUploadResourceHelper {

    @Autowired
    @Value("\${uploads.storage.location}")
    lateinit var appUploadPath: String

    fun uploadFile(file: MultipartFile, directory : String) : FileDetailsModel {
        //val UPLOAD_PATH = Paths.get(ClassPathResource("").file.absolutePath + File.separator + "uploads"  + File.separator + "agentInvoices" + File.separator + directory)
        val UPLOAD_PATH =  Paths.get(appUploadPath + File.separator + "agentInvoices" + File.separator + directory)
        if (!Files.exists(UPLOAD_PATH)) {
            Files.createDirectories(UPLOAD_PATH)
        }

        // file format validation
        if (!file.contentType.equals("application/pdf")) {
            throw Exception("only pdf files are supported ="+file.contentType)
        }

        val timeStampedFileName: String = SimpleDateFormat("ssmmHHddMMyyyy")
            .format(Date()) + "_" + file.originalFilename

        val filePath: Path = UPLOAD_PATH.resolve(timeStampedFileName)

        Files.copy(file.inputStream, filePath)

        var fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/$directory/").path(timeStampedFileName).toUriString()

        //val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        //    .path("/file/download/").path(timeStampedFileName).toUriString()
        val fileDownloadUri = filePath.toUri().toString()

        val fileDetails = FileDetailsModel(null, file.originalFilename, fileUri, fileDownloadUri, file.size, Calendar.getInstance().time)

        return fileDetails
    }

    fun downloadFile(path: String) : ByteArray{
        val resource: Resource = UrlResource(path)

        return resource.file.readBytes()
    }
}