package cn.kloping.lsys.utils

import cn.kloping.lsys.entitys.Request
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.message.data.*
import java.io.File
import java.io.IOException
import java.net.URL
import java.util.*

fun toText(chain: MessageChain): String {
    val sb = StringBuilder();
    for (e in chain) {
        when (e) {
            is MessageSource -> continue
            is PlainText -> {
                sb.append(e.content)
            }
            is At -> {
                sb.append("[@${e.target}]")
            }
            is FlashImage -> {
                sb.append("[闪照(${e.image.imageId})]")
            }
            is Image -> {
                sb.append("[图片(${e.imageId})]")
            }
            is Audio -> {
                sb.append("[语音(${e.filename})]")
            }
            else -> {
                sb.append("[$e]")
            }
        }
    }
    return sb.toString();
}

public object MessageUtils {

    @JvmField
    public val random = Random();

    @JvmStatic
    fun createImageInGroup(group: Contact, path: String): Image? {
        return try {
            if (path.startsWith("http")) {
                runBlocking { group.uploadImage(URL(path).openStream()) }
            } else if (path.startsWith("{")) {
                Image.fromId(path)
            } else {
                runBlocking { group.uploadImage(File(path)) }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            System.err.println(path + "加载失败")
            null
        }
    }

    @JvmStatic
    fun getAtFromRequest(request: Request): Long {
        for (m in request.event.message) {
            if (m is At) return m.target;
        }
        return -1;
    }

    @JvmStatic
    fun getAllAtFromRequest(request: Request): Array<Long> {
        var list = emptyArray<Long>()
        for (m in request.event.message) {
            if (m is At) list = list.plus(m.target)
        }
        return list
    }

}