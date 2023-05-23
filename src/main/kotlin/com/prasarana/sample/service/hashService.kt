package com.prasarana.sample.service

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class HashService {
    fun checkBcrypt(input:String, hash:String):Boolean{
        return BCrypt.checkpw(input, hash)
    }
}