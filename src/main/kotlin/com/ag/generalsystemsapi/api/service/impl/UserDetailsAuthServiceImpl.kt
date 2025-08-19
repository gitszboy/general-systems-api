package com.ag.generalsystemsapi.api.service.impl

import com.ag.generalsystemsapi.api.model.UsersModel
import com.ag.generalsystemsapi.api.repository.UserRolesRepository
import com.ag.generalsystemsapi.api.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserDetailsAuthServiceImpl : UserDetailsService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    lateinit var userRolesRepository: UserRolesRepository

    override fun loadUserByUsername(username: String?): UserDetails {

        val user: UsersModel = usersRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User not found with username or email: $username") }

        var roles: String? = null
        for(r in userRolesRepository.findByUserRolesUserId(Optional.of(user))){
            roles = roles.plus(r.userRolesRlId?.roleId.toString())
        }

        val authorities = roles
            ?.split(",")?.filterNot { it.isEmpty() }
            ?.map(::SimpleGrantedAuthority)

        return User(
            user.username,
            user.password,
            authorities
        )
    }
}