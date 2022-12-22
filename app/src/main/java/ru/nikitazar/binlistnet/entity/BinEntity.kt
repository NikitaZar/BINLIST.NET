package ru.nikitazar.binlistnet.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.nikitazar.binlistnet.dto.CardBin

@Entity
data class BinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val bin: Int
) {
    fun toDto() = CardBin(
        id = id,
        bin = bin
    )

    companion object {
        fun fromDto(dto: CardBin) = BinEntity(
            id = dto.id,
            bin = dto.bin
        )
    }
}

fun List<BinEntity>.toDto() = map { it.toDto() }
fun List<CardBin>.toEntity() = map { BinEntity.fromDto(it) }
