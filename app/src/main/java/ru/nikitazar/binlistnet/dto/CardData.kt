package ru.nikitazar.binlistnet.dto

data class CardData(
    val number: CardNumber? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: CardCountry? = null,
    val bank: CardBank? = null
)