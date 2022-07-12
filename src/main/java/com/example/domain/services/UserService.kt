package com.example.domain.services

import com.example.domain.*
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.UserLoginRequest
import com.example.domain.requests.UserRegistrationRequest
import com.example.domain.responses.UserResponse
import com.example.infrastructure.security.BCryptHashProvider
import com.example.infrastructure.security.JwtTokenProvider
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(
    private val repository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val hashProvider: BCryptHashProvider,
) {
    fun get(username: String): UserResponse = repository.findByUsername(username)?.run {
        UserResponse.build(this, tokenProvider.create(username, role)) // TODO maybe don't return the token
    } ?: throw UserNotFoundException()

    fun register(newUser: UserRegistrationRequest): UserResponse = newUser.run {
        if (repository.existsUsername(newUser.username)) throw UsernameAlreadyExistsException()
        if (repository.existsEmail(newUser.email)) throw EmailAlreadyExistsException()

        UserResponse.build(
            this.toEntity().also {
                it.password = hashProvider.hash(password)
                repository.persist(it)
            },
            tokenProvider.create(username)
        )
    }

    fun login(userLoginRequest: UserLoginRequest) = repository.findByEmail(userLoginRequest.email)?.run {
        if (!hashProvider.verify(userLoginRequest.password, password)) throw InvalidPasswordException()
        else UserResponse.build(this, tokenProvider.create(username, role))
    } ?: throw UnregisteredEmailException()

    /*fun update(loggedInUserId: String, updateRequest: UserUpdateRequest): UserResponse = repository
        .findById(loggedInUserId)
        ?.run {
            if (updateRequest.username != null &&
                updateRequest.username != username &&
                repository.existsUsername(updateRequest.username)
            ) throw UsernameAlreadyExistsException()

            if (updateRequest.email != null &&
                updateRequest.email != email &&
                repository.existsEmail(updateRequest.email)
            ) throw EmailAlreadyExistsException()

            UserResponse.build(
                updateRequest.applyChangesTo(this).apply {
                    if (updateRequest.password != null) this.password = hashProvider.hash(password)
                    repository.persist(this)
                },
                tokenProvider.create(username)
            )
        } ?: throw UserNotFoundException()*/
}
