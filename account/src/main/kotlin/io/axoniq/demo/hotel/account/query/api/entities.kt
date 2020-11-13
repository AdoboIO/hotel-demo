package io.axoniq.demo.hotel.account.query.api

import io.axoniq.demo.hotel.account.command.web.PaymentStatus
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
data class AccountEntity(@Id var accountId: String, var userName: String, var password: String)

@Entity
data class PaymentEntity(@Id var paymentId: String, var accountId: UUID, var totalAmount: BigDecimal, @Enumerated(EnumType.STRING) var paymentStatus: PaymentStatus)
