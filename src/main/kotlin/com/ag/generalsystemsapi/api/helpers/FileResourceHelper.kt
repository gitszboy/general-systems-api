package com.ag.generalsystemsapi.api.helpers

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

class FileResourceHelper {
    @Throws(URISyntaxException::class)
    fun getFileFromResource(fileName: String): File {
        val classLoader = javaClass.classLoader
        val resource: URL? = classLoader.getResource(fileName)
        return if (resource == null) {
            throw IllegalArgumentException("file not found! $fileName")
        } else {
            File(resource.toURI())
        }
    }
}