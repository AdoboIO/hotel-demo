package io.axoniq.demo.hotel.account.command.web.api

import io.axoniq.demo.hotel.account.command.web.PaymentStatus
import java.math.BigDecimal
import java.util.*

data class AccountRequestData(val userName: String, val password: String)
data class AccountResponseData(val accountId: String, val userName: String, val password: String)
data class PayRequestData(val accountId: UUID, val totalAmount: BigDecimal)
data class PaymentResponseData(val paymentId: String, val accountId: UUID, val totalAmount: BigDecimal, val paymentStatus: PaymentStatus)

