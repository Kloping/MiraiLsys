package cn.kloping.lsys.utils

import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.message.data.*
import java.io.File
import java.io.IOException
import java.net.URL

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

object MessageUtils {

    @JvmStatic
    suspend fun createImageInGroup(group: Contact, path: String): Image? {
        return try {
            if (path.startsWith("http")) {
                group.uploadImage(URL(path).openStream())
            } else if (path.startsWith("{")) {
                Image.fromId(path)
            } else {
                group.uploadImage(File(path))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            System.err.println(path + "加载失败")
            null
        }
    }

}