package com.prasarana.sample.config
import com.prasarana.sample.models.UserModel
import org.springframework.security.core.Authentication

/**
 * Shorthand for controllers accessing the authenticated user.
 */
fun Authentication.toUser(): UserModel {
    return principal as UserModel
}
