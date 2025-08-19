package com.ag.generalsystemsapi.api.helpers

data class PasswordValidationResult(
    val errors: MutableList<String> = mutableListOf()
) {
    val isValid: Boolean
        get() = errors.isEmpty()

    fun addError(error: String) {
        errors.add(error)
    }
}