package com.muhammet.account.dto

import com.muhammet.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccountDto(
    val id:String,
    var balance:BigDecimal?=BigDecimal.ZERO,
    val transactions: Set<TransactionDto>,
    val creationDate:LocalDateTime
){

}
