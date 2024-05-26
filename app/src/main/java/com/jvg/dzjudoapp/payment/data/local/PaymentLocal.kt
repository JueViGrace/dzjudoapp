package com.jvg.dzjudoapp.payment.data.local

import kotlinx.coroutines.flow.Flow
import com.jvg.dzjudoapp.Payment as PaymentEntity

interface PaymentLocal {
    fun getPayments(): Flow<List<PaymentEntity>>

    fun getPayment(id: String): Flow<PaymentEntity>

    suspend fun addPayment(payments: PaymentEntity)

    suspend fun deletePayments()
}
