package com.ag.generalsystemsapi.api.model

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "system_users")
class UsersModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var userId: Long? = null,

    @Column(name = "user_full_name", nullable = false)
    var userFullName: String,

    @Column(name = "user_username", nullable = false)
    var username: String,

    @Column(name = "user_password", nullable = false)
    var password: String,

    @Column(name = "user_active", nullable = true)
    var userActive: Boolean,

    @Column(name = "user_email", nullable = true)
    var userEmail: String? = null,

    @Column(name = "user_type", nullable = true)
    var userType: String? = "P",

    @Column(name = "user_pass_reset", nullable = true)
    var userPasswordReset: String? = "N",

    @Column(name = "user_pass_reset_code", nullable = true)
    var userPasswordResetCode: Long? = null,

    @Column(name = "user_pass_reset_date", nullable = true)
    var userPasswordResetDate: Date? = null,
)