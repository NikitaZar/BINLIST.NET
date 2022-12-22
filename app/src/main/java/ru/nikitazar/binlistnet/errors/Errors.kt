package ru.nikitazar.binlistnet.errors

import java.io.IOException
import java.sql.SQLException

sealed class AppError(val code: Int, val info: String) : RuntimeException(info) {
    companion object {
        fun from(e: Throwable): AppError = when (e) {
            is AppError -> e
            is SQLException -> DbError
            is IOException -> NetworkError
            else -> UnknownError
        }
    }
}

class ApiError(code: Int, message: String) : AppError(code, message)

object NetworkError : AppError(-1, "no_network")
object DbError : AppError(-2, "error_db")
object UnknownError : AppError(-3, "unknown")