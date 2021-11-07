package com.example.cdcapplication.domain

import kotlin.random.Random

data class BankAccount(
    val bankAccountNumber: String = (Random.nextFloat()*10).toString() + (Random.nextFloat()*10).toString(),
    val balance: Float = Random.nextDouble(2000.0, 41000.0).toFloat(),
    val iban: String = "AD1400080001001234567890"
)