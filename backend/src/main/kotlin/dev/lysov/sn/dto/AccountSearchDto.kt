package dev.lysov.sn.dto

/**
 * ДТО для поиска и фильтрации анкет.
 */
data class AccountSearchDto(
        val limit: Int? = null,
        val offset: Int? = null,
        val name: String? = null
)