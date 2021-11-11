package cn.kloping.lsys.workers

import cn.kloping.lsys.Resource
import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.entitys.Request
import cn.kloping.lsys.entitys.Result
import cn.kloping.lsys.entitys.User
import cn.kloping.number.NumberUtils
import java.util.concurrent.ConcurrentHashMap

object Methods {
    @JvmField
    public val invokes = ConcurrentHashMap<String, (arg: User, args: Request?) -> Result?>();


    fun init() {
        val clasz: Class<*> = Methods.javaClass;
        val fs = clasz.declaredFields;
        for (f in fs) {
            try {
                invokes.put(f.name, f.get(null) as (arg: User, args: Request?) -> Result?)
            } catch (e: Exception) {
                continue
            }
        }
    }

    /**
     * 查积分
     */
    @JvmField
    val m1: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val result = Result(arrayOf(user.p, user.p2), 0);
        result
    }

    /**
     * 取积分
     */
    @JvmField
    val m2: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val t1 = any?.str
        val numStr = NumberUtils.findNumberFromString(t1);
        if (numStr.isNullOrBlank()) Result(null, 2)
        else {
            val num: Number = java.lang.Long.parseLong(numStr)
            if (user.getP2(num as Long)) Result(null, 0)
            else Result(null, 1)
        }
    }

    /**
     * 存积分
     */
    @JvmField
    val m3: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val t1 = any?.str
        val numStr = NumberUtils.findNumberFromString(t1);
        if (numStr.isNullOrBlank()) Result(null, 2)
        else {
            val num: Number = java.lang.Long.parseLong(numStr)
            if (user.putP(num as Long)) Result(null, 0)
            else Result(null, 1)
        }
    }

    /**
     * 开启
     */
    @JvmField
    val mOpen: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val gq: Number = any?.gId!!
        if (user.qq == Resource.conf.qq) {
            if (Resource.conf.opens.contains(gq)) {
                Result(null, 1)
            } else {
                Resource.conf.opens = Resource.conf.opens.plusElement(gq)
                Resource.conf.apply()
                Result(null, 0)
            }
        } else {
            null
        }
    }

    /**
     * 关闭
     */
    @JvmField
    val mClose: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val gq: Number = any?.gId!!
        if (user.qq == Resource.conf.qq) {
            if (Resource.conf.opens.contains(gq)) {
                Resource.conf.opens = Resource.conf.opens.apply {
                    for ((i, gi) in this.withIndex()) {
                        when (gi) {
                            gq -> {
                                this.drop(i)
                                break
                            }
                        }
                    }
                }
                Resource.conf.apply()
                Result(null, 0)
            } else {
                Result(null, 1)
            }
        } else {
            null
        }
    }

    @JvmField
    val method: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        Result(null, 0)
    }

    @JvmField
    val mpOpen: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        if (user.qq == Resource.conf.qq) {
            Resource.conf.prK = true
            Resource.conf.apply()
            Result(null, 0)
        } else
            null
    }

    @JvmField
    val mpClose: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        if (user.qq == Resource.conf.qq) {
            Resource.conf.prK = false
            Resource.conf.apply()
            Result(null, 0)
        } else
            null
    }

    @JvmField
    val menuN: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val sb = StringBuilder()
        var n = 1;
        for (e in conf.invokes.keys()) {
            var e1 = if (e.endsWith(".*")) e.replace(".*", " (参数)") else e
            sb.append(n).append(".").append(e1).append("\n")
            n++
        }
        Result(arrayOf(sb.toString()), 0)
    }
}