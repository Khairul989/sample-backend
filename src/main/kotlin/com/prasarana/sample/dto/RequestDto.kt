package com.prasarana.sample.dto

data class LoginDto(
    val email: String,
    val password: String,
)

data class RegisterDto(
    val email: String,
    val password: String,
    val name: String,
)