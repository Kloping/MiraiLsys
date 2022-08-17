package cn.kloping.lsys.savers

import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.entitys.User
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import io.github.kloping.file.FileUtils
import java.io.File
import java.util.concurrent.ConcurrentHashMap

object PutGetter {
    @JvmField
    val HIST_USER = ConcurrentHashMap<Long, User>();

    @JvmStatic
    fun save(user: User) {
        HIST_USER[user.qq.toLong()] = user
        FileUtils.putStringInFile(JSON.toJSONString(user), File(conf.path, user.qq.toString() + "/data"))
    }

    @JvmStatic
            /**
             * 获取某人的信息 若不存在 则返回 null
             */
    fun get(q: Long): User? {
        if (HIST_USER.containsKey(q))
            return HIST_USER[q]!!
        val user: User
        val str = FileUtils.getStringFromFile(File(conf.path, "$q/data").path);
        if (!str.isNullOrBlank()) {
            val jo: JSONObject = JSON.parseObject(str);
            user = jo.toJavaObject(Class.forName("cn.kloping.lsys.entitys.User")) as User
            HIST_USER[user.qq.toLong()] = user
            return user;
        }
        return null
    }

    @JvmStatic
            /**
             * k: 是否强制生成
             */
    fun get(q: Long, k: Boolean): User {
        if (HIST_USER.containsKey(q))
            return HIST_USER[q]!!
        val user: User
        val str = FileUtils.getStringFromFile(File(conf.path, "$q/data").path);
        if (str.isNullOrBlank() && k) {
            user = User(q, 1000, 200, 0, 0)
            user.apply()
        } else {
            val jo: JSONObject = JSON.parseObject(str);
            user = jo.toJavaObject(Class.forName("cn.kloping.lsys.entitys.User")) as User
        }
        HIST_USER[user.qq.toLong()] = user
        return user;
    }

}
