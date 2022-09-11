package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.*
import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.requests.UserLoginRequest
import cz.ssps.gradingsystem.domain.requests.UserRegistrationRequest
import cz.ssps.gradingsystem.domain.responses.ProfileResponse
import cz.ssps.gradingsystem.domain.responses.UserResponse
import cz.ssps.gradingsystem.infrastructure.security.BCryptHashProvider
import cz.ssps.gradingsystem.infrastructure.security.JwtTokenProvider
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

    /**  */
    fun getAll(): List<ProfileResponse> {
        return repository.findAll().list().map {
            ProfileResponse.build(user = it)
        }
    }

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
