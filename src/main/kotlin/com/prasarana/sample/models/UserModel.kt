package com.prasarana.sample.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
@Entity(name = "User")
@Table(name = "users", schema = "sample")
data class UserModel (
        // Add more properties as needed
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_id_seq")
        @SequenceGenerator(name = "api_user_id_seq", allocationSize = 1)
        var id: Long = 0,
        @Column(nullable = false)
        var password: String? = null,
        var name: String? = null,
        var email: String? = null
)