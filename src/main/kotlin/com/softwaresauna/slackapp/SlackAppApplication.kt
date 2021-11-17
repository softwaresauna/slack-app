package com.softwaresauna.slackapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@SpringBootApplication
class SlackAppApplication

fun main(args: Array<String>) {
    runApplication<SlackAppApplication>(*args)
}

@RestController
class UrlVerifier {

    data class VerifyUrl(
        val token: String,
        val challenge: String,
        val type: String
    )
    data class UrlVerification(
        val challenge: String,
    )

    @PostMapping
    fun verifyUrl(@RequestBody verifyRequest: VerifyUrl): UrlVerification {

        if(verifyRequest.type != "url_verification") {
            throw IllegalArgumentException("Invalid type! Expected: url_verification")
        }

        return UrlVerification(challenge = verifyRequest.challenge)
    }
}
