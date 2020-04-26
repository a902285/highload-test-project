package dev.lysov.sn.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class DebugController(
        @Value("\${spring.datasource.master.jdbc-url}")
        private val masterDatasourceUrl: String,
        @Value("\${spring.datasource.slave.jdbc-url}")
        private val slaveDatasourceUrl: String
) {

    @GetMapping("/datasource/master/url")
    fun masterDatasourceUrl() = masterDatasourceUrl

    @GetMapping("/datasource/slave/url")
    fun slaveDatasourceUrl() = slaveDatasourceUrl
}