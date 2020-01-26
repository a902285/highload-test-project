package dev.lysov.sn.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountControllerImpl {

    @GetMapping("/test")
    fun test() = "Social network"
}