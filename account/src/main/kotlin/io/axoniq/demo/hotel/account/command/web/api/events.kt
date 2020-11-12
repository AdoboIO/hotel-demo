package io.axoniq.demo.hotel.account.command.web.api

import java.math.BigDecimal
import java.util.*

// Account
data class AccountRegisteredEvent(val accountId: UUID, val userName: String, val password: String)

// Payment
data class PaymentRequestedEvent(val paymentId: UUID, val accountId: UUID, val totalAmount: BigDecimal)
data class PaymentSucceededEvent(val paymentId: UUID)
