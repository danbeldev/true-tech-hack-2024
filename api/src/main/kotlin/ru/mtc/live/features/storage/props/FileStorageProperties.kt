package ru.mtc.live.features.storage.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "file")
class FileStorageProperties {
    lateinit var uploadDir: String
}