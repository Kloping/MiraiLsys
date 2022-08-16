package cn.kloping.lsys.entitys

import cn.kloping.lsys.savers.PutGetter.save
import io.github.kloping.date.DateUtils

data class User(
    /**
     * qq
     */
    val qq: Number = -1,
    /**
     * 积分
     */
    var p: Long = 1000,
    /**
     * 存的积分
     */
    var p2: Long = 200,
    /**
     * 犯罪指数
     */
    var pf: Long = 0,
    /**
     * 上次注册天
     */
    var ur: Int = 0,
    /**
     * 打工冷却
     */
    var k1: Long = 0,
    /**
     * 发言次数
     */
    var speeches: Long = 0,
    /**
     * 发言当日
     */
    var speeches_day: Int = 0,
    /**
     * 发言总次数
     */
    var speeches_total: Long = 0,
) : Entity {

    constructor() : this(-1, 0, 0, 0, 0, 0)

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

    fun addP(n: Number): User {
        p += n.toLong()
        return this;
    }

    fun addP2(n: Number): User {
        p2 += n.toLong();
        return this;
    }

    fun addPf(n: Int): User {
        pf += n
        return this;
    }

    fun addSpeeches(): User {
        speeches++;
        speeches_total++;
        val d0 = DateUtils.getDay()
        if (speeches_day != d0) {
            speeches_day = d0;
            speeches = 0;
        }
        return this;
    }

    override fun apply() {
        save(this)
    }
}