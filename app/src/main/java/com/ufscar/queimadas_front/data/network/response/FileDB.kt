package com.ufscar.queimadas_front.data.network.response

import java.util.*

data class FileDB(
    val name: String,
    val type: String,
    val data: ByteArray,
    val id: UUID
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileDB

        if (name != other.name) return false
        if (type != other.type) return false
        if (!data.contentEquals(other.data)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}