package com.example.cdcapplication.domain

import android.content.Context
import android.text.format.DateFormat
import java.util.*
import kotlin.random.Random


data class BankTransfer(
    val from: String = "",
    val to: String = "",
    val amount: Float = Random.nextFloat()*10,
    val bankAccount: String = (Random.nextFloat()*10).toString() + (Random.nextFloat()*10).toString(),
    val description: String = when(Random.nextInt(0, 3)){
        0 -> "Condominium fees"
        1 -> "Wire transfer"
        2 -> "Pharmacy"
        else -> "Condominium fees"
    },
    val date: String = DateFormat.format("yyyy-MM-dd", Date()).toString(),
    val bankTransferType: BankTransferType =
        when(Random.nextInt(0, 3)){
            0 -> BankTransferType.HOME
            1 -> BankTransferType.TRAVEL
            2 -> BankTransferType.FOOD
            else -> BankTransferType.HOME
        },
    val isAccounted: Boolean = Random.nextBoolean()
    )



enum class BankTransferType{
    HOME,
    TRAVEL,
    FOOD
}