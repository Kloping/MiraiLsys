package cn.kloping.lsys.entitys

import cn.kloping.lsys.savers.PutGetter.save


data class User(
    val qq: Number,
    var p: Long,
    var p2: Long,
    var pf: Long,
) : Entity {

    constructor() : this(-1, 0, 0, 0)

    fun getP2(n: Long): Boolean {
        if (n <= this.p2) {
            p2 -= n
            p += n
            apply()
            return true
        }
        return false
    }

    fun putP(n: Long): Boolean {
        if (n <= this.p) {
            p -= n
            p2 += n
            apply()
            return true
        }
        return false
    }

    override fun apply() {
        save(this)
    }
}