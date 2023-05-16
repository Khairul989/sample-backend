package com.prasarana.sample.service

import com.prasarana.sample.models.UserModel
import com.prasarana.sample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {
    fun createUser(user: UserModel): UserModel {
        // Add any business logic or validation before saving the user
        return userRepository.save(user)
    }

    fun getUser(id: Long): UserModel {
        return userRepository.findById(id.toInt()).orElse(null)
    }

    fun updateUser(id: Long, updatedUser: UserModel): UserModel? {
        val existingUser: UserModel? = userRepository.findById(id.toInt()).orElse(null)
        if (existingUser != null) {
            // Update the existing user with the new data
            existingUser.name = updatedUser.name
            existingUser.email = updatedUser.email
            // ... Update other properties as needed

            // Save the updated user
            return userRepository.save(existingUser!!)
        }
        return null
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id.toInt())
    }
}
