package cn.kloping.lsys.workers

import cn.kloping.lsys.Resource
import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.entitys.Request
import cn.kloping.lsys.entitys.Result
import cn.kloping.lsys.entitys.User
import cn.kloping.lsys.parseType
import cn.kloping.lsys.toLink
import io.github.kloping.number.NumberUtils
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.code.MiraiCode
import net.mamoe.mirai.message.data.MessageChainBuilder
import java.util.concurrent.ConcurrentHashMap

object Methods {

    @JvmField
    val state0 = Result(arrayOf(), 0)

    @JvmField
    val state1 = Result(arrayOf(), 1)

    @JvmField
    val state2 = Result(arrayOf(), 2)

    @JvmField
    val state3 = Result(arrayOf(), 3)

    @JvmField
    val state4 = Result(arrayOf(), 4)

    @JvmField
    val state5 = Result(arrayOf(), 5)

    @JvmField
    val state6 = Result(arrayOf(), 6)


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
        if (Resource.conf.hasPerm(user.qq.toLong())) {
            if (conf.opens.add(gq.toLong())) {
                Resource.conf.apply()
                Result(null, 0)
            } else {
                Result(null, 1)
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
        if (Resource.conf.hasPerm(user.qq.toLong())) {
            if (Resource.conf.opens.contains(gq)) {
                conf.opens.remove(gq);
                conf.apply();
                Result(null, 0)
            } else {
                Result(null, 1)
            }
        } else {
            null
        }
    }

    /**
     * 开启
     */
    @JvmField
    val mOpen0: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val t1 = any?.str
        val numStr = NumberUtils.findNumberFromString(t1);
        if (numStr.isNullOrEmpty()) {
            null
        } else {
            val gq: Number = Integer.parseInt(numStr);
            if (Resource.conf.hasPerm(user.qq.toLong())) {
                if (conf.opens.add(gq.toLong())) {
                    Resource.conf.apply()
                    Result(null, 0)
                } else {
                    Result(null, 1)
                }
            } else {
                null
            }
        }
    }

    /**
     * 关闭
     */
    @JvmField
    val mClose0: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        val t1 = any?.str
        val numStr = NumberUtils.findNumberFromString(t1);
        if (numStr.isNullOrEmpty()) {
            null
        } else {
            val gq: Number = Integer.parseInt(numStr);
            if (Resource.conf.hasPerm(user.qq.toLong())) {
                if (Resource.conf.opens.contains(gq)) {
                    conf.opens.remove(gq);
                    conf.apply();
                    Result(null, 0)
                } else {
                    Result(null, 1)
                }
            } else {
                null
            }
        }
    }

    @JvmField
    val method: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        Result(null, 0)
    }

    @JvmField
    val mpOpen: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        if (Resource.conf.hasPerm(user.qq.toLong())) {
            Resource.conf.prK = true
            Resource.conf.apply()
            Result(null, 0)
        } else
            null
    }

    @JvmField
    val mpClose: (arg: User, args: Request?) -> Result? = { user: User, any: Request? ->
        if (Resource.conf.hasPerm(user.qq.toLong())) {
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

    @JvmStatic
    public fun execute0(vararg args: Any, oText: String, contact: Contact, id: Long) {
        var resText = oText
        if (resText.trim().startsWith("[") && resText.trim().endsWith("]")) {
            val codeStr = resText.substring(1, resText.length - 1)
            val me = MiraiCode.deserializeMiraiCode(codeStr)
            runBlocking { contact.sendMessage(me) }
            return
        }
        args.let {
            var i = 1;
            for (e in it) {
                val tp1 = "$" + i++;
                resText = resText.replace(tp1, e.toString());
            }
        }
        val mb = MessageChainBuilder()
        for (e in toLink(resText)) {
            runBlocking { parseType(e.toString(), contact, id)?.let { it1 -> mb.append(it1) } }
        }
        if (!mb.isEmpty()) {
            runBlocking { contact.sendMessage(mb.build()) }
        }
    }
}