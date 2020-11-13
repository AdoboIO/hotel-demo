package io.axoniq.demo.hotel.account.query.api

import java.util.*

//Account
data class FindAccount(val accountId: UUID)
class FindAccounts

//Payment
data class FindPayment(val paymentId: UUID)
class FindPayments
data class FindPaymentsForAccount(val accountId: UUID)
