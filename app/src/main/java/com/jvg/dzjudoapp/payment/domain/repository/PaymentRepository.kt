package com.jvg.dzjudoapp.payment.domain.repository

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.payment.data.source.PaymentDataSource
import com.jvg.dzjudoapp.payment.domain.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    val paymentDataSource: PaymentDataSource

    fun getPayments(): Flow<RequestState<List<Payment>>>

    fun getPayment(id: String): Flow<RequestState<Payment>>

    suspend fun addPayment(payment: Payment)

    suspend fun deletePayments()
}
