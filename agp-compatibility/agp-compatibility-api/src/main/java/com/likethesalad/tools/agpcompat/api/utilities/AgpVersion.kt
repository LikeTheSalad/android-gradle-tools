package com.likethesalad.tools.agpcompat.api.utilities

data class AgpVersion(
    val major: Int,
    val minor: Int = 0,
    val micro: Int = 0
) : Comparable<AgpVersion> {

    private companion object {
        val comparator: Comparator<AgpVersion> =
            Comparator.comparingInt<AgpVersion> { it.major }
                .thenComparingInt { it.minor }
                .thenComparingInt { it.micro }
    }

    override fun compareTo(other: AgpVersion): Int {
        return comparator.compare(this, other)
    }
}