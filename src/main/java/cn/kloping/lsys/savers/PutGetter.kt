package cn.kloping.lsys.savers

import cn.kloping.file.FileUtils
import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.entitys.User
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import java.io.File
import java.util.concurrent.ConcurrentHashMap

object PutGetter {
    val histUser = ConcurrentHashMap<Long, User>();


    @JvmStatic
    fun save(user: User) {
        histUser[user.qq.toLong()] = user
        FileUtils.putStringInFile(JSON.toJSONString(user), File(conf.path, user.qq.toString() + "/data"))
    }

    @JvmStatic
    fun get(q: Long): User {
        if (histUser.containsKey(q))
            return histUser[q]!!
        val user: User
        val str = FileUtils.getStringFromFile(File(conf.path, "$q/data").path);
        if (str.isNullOrBlank()) {
            user = User(q, 1000, 200, 0)
            user.apply()
        } else {
            val jo: JSONObject = JSON.parseObject(str);
            user = jo.toJavaObject(Class.forName("cn.kloping.lsys.entitys.User")) as User
        }
        histUser[user.qq.toLong()] = user
        return user;
    }
}
