package ru.mtc.live.features.storage.services

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import ru.mtc.live.common.exceptions.ResourceNotFoundException
import ru.mtc.live.features.storage.props.FileStorageProperties
import java.io.IOException
import java.nio.file.Paths

@Service
class FileStorageServiceImpl(
    private val fileStorageProperties: FileStorageProperties
): FileStorageService {

    override fun loadFileAsResource(fileName: String): Resource {
        try {
            val filePath = Paths.get(fileStorageProperties.uploadDir).resolve(fileName).normalize()
            val resource: Resource = UrlResource(filePath.toUri())
            if (resource.exists()) {
                return resource
            } else {
                throw ResourceNotFoundException("File not found: $fileName")
            }
        } catch (ex: IOException) {
            throw ResourceNotFoundException("File not found: $fileName")
        }
    }
}