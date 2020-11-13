package io.axoniq.demo.hotel.account.command.web.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

// Account
data class RegisterAccountCommand(
        @TargetAggregateIdentifier val accountId: UUID,
        val userName: String,
        val password: String
)

// Payment
data class PayCommand(val paymentId: UUID, @TargetAggregateIdentifier val accountId: UUID, val totalAmount: BigDecimal)
data class ProcessPaymentCommand(@TargetAggregateIdentifier val paymentId: UUID)
