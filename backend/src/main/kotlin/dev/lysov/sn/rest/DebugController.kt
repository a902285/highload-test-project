package dev.lysov.sn.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class DebugController(
        @Value("\${spring.datasource.url}")
        private val datasourceUrl: String
) {

    @GetMapping("/datasource/url")
    fun datasourceUrl() = datasourceUrl
}