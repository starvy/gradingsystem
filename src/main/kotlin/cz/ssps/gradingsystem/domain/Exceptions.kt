package cz.ssps.gradingsystem.domain

import io.quarkus.security.ForbiddenException

class UsernameAlreadyExistsException : RuntimeException("Username is already registered to another email")
class EmailAlreadyExistsException : RuntimeException("Email is already registered to another username")
class UserNotFoundException : RuntimeException("User not found")
class InvalidPasswordException : ForbiddenException("Invalid password")
class InvalidUserException : ForbiddenException("User not permitted to perform this action")
class UnregisteredEmailException : RuntimeException("Email is not registered to any user")

class UserIsInGroup : java.lang.RuntimeException("User is already in that group")
