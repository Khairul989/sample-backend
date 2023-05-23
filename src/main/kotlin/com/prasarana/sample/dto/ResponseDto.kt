package com.prasarana.sample.dto

import org.springframework.web.server.ResponseStatusException

class ApiException(code: Int, message: String) : ResponseStatusException(code, message, null)

data class LoginResponseDto(
    val user: RegisterDto,
    val token: String,
)
