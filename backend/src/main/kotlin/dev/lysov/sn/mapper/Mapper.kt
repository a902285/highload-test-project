package dev.lysov.sn.mapper

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.SignupRequestDto
import dev.lysov.sn.model.Account

fun Account.toAccountDto() = AccountDto(
        id = id!!,
        username = username!!,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        city = city,
        description = description
)

fun AccountDto.toAccount() = Account(
        id = id,
        username = username,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        city = city,
        description = description
)

fun SignupRequestDto.toAccount() = Account(
        username = username,
        password = password,
        firstName = firstName,
        lastName = lastName,
        age = age,
        gender = gender,
        city = city,
        description = description
)