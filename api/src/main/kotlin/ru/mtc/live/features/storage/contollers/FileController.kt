package ru.mtc.live.features.storage.contollers

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.mtc.live.features.storage.services.FileStorageService
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/files")
class FileController(
    private val fileStorageService: FileStorageService
) {

    @Hidden
    @GetMapping("/{filename}")
    fun downloadFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val resource = fileStorageService.loadFileAsResource(filename)
        val contentType = getContentType(filename)

        return ResponseEntity.ok()
            .contentType(contentType)
            .body(resource)
    }

    private fun getContentType(filename: String): MediaType {
        val mimeType = Files.probeContentType(Paths.get(filename)) ?: "application/octet-stream"
        return MediaType.parseMediaType(mimeType)
    }
}