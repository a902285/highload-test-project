package dev.lysov.sn.dto

data class PageDto<T>(
        val limit: Int? = null,
        val offset: Int? = null,
        val total: Long? = null,
        val data: List<T> = emptyList()
)