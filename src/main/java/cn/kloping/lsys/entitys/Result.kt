package cn.kloping.lsys.entitys

data class Result(
    var returnArgs: Array<Any>?,
    val state: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (!returnArgs.contentEquals(other.returnArgs)) return false

        return true
    }

    override fun hashCode(): Int {
        return returnArgs.contentHashCode()
    }
}
