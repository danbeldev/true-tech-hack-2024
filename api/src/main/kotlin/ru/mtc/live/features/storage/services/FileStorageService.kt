package ru.mtc.live.features.storage.services

import org.springframework.core.io.Resource

interface FileStorageService {

    fun loadFileAsResource(fileName: String): Resource
}