package com.jvg.dzjudoapp.payment.data.source

import com.jvg.dzjudoapp.payment.data.local.PaymentLocal

interface PaymentDataSource {
    val paymentLocal: PaymentLocal
}
