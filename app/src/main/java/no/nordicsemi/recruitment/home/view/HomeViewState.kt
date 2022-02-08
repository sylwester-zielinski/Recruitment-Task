package no.nordicsemi.recruitment.home.view

import no.nordicsemi.recruitment.utils.EMPTY

data class HomeViewState(
    val tasks: List<String>,
    val displayedText: String = String.EMPTY,
    val error: FieldError? = null
)

enum class FieldError {
    EMPTY, ALREADY_EXIST
}