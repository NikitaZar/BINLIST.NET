package ru.nikitazar.binlistnet.dto

data class CardNumber(
    val length: Int?,
    val luhn: Boolean?
)