package com.jvg.dzjudoapp.payment.domain.model

data class Payment(
    val id: String = "",
    val studentId: String = "",
    val paymentMode: String = "",
    val bank: String = "",
    val amount: String = "",
    val correspondsTo: String = "",
)
