package com.prasarana.sample.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users")
class UserModel {

        // Add more properties as needed
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, updatable = false)
        var id: Long? = null
                private set
        var name: String? = null
        var username: String? = null
        var email: String? = null

//        // Constructor without the id parameter
//        constructor(name: String?, username: String?, email: String?) : this(email, name, username) {
//                this.name = name
//                this.username = username
//                this.email = email
//        }
}
