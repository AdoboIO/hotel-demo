package io.axoniq.demo.hotel.account.command.web.api

data class AccountRequestData(val userName: String, val password: String)
data class AccountResponseData(val accountId: String, val userName: String, val password: String)
