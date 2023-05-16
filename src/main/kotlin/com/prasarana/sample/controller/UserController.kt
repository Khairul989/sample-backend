package com.prasarana.sample.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import com.prasarana.sample.models.UserModel
import com.prasarana.sample.service.UserService

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(private val userService: UserService) {

    // Create a new user
    @PostMapping
    fun createUser(@RequestBody user: UserModel): UserModel {
        // Access the properties using the setter methods
        user.name = "John Doe"
        user.username = "johndoe"
        user.email = "johndoe@example.com"

        return userService.createUser(user)
    }

    // Get a user by ID
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long): UserModel {
        val user = userService.getUser(id)

        // Access the properties using the getter methods
        val userId = user.id
        val name = user.name
        val username = user.username
        val email = user.email

        // Process the user object as needed
        // ...

        return user
    }

    // Update a user
    @PutMapping("/{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody updatedUser: UserModel): UserModel? {
        val user = userService.getUser(id)

        // Access the properties using the setter methods
        user.name = updatedUser.name
        user.username = updatedUser.username
        user.email = updatedUser.email

        return userService.updateUser(id, user)
    }

    // Delete a user
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Long) {
        userService.deleteUser(id)
    }
}
