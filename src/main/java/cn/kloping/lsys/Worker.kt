package cn.kloping.lsys

import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.entitys.Request
import cn.kloping.lsys.savers.PutGetter
import cn.kloping.lsys.utils.MessageUtils.createImageInGroup
import cn.kloping.lsys.workers.Methods.invokes
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.code.MiraiCode
import net.mamoe.mirai.message.data.*
import java.lang.System.err
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.regex.Matcher
import java.util.regex.Pattern

val threads = Executors.newFixedThreadPool(16)
val showeds = ConcurrentHashMap<Long, Boolean>()

suspend fun handMessage(str: String, event: MessageEvent) {
    if (!conf.opens.contains(-1) && !conf.opens.contains(event.subject.id) && event.sender.id != conf.qq) {
        if (!showeds.containsKey(event.subject.id) || !showeds[event.subject.id]!!) {
            println("未开启群: " + event.subject.id)
            showeds[event.subject.id] = true
        }
        return
    }
    threads.execute { runBlocking { run(str, event) } }
}

val histMat = ConcurrentHashMap<String, String>();

val MethodName2Ostr = ConcurrentHashMap<String, String>();

suspend fun run(str: String, event: MessageEvent) {
    var text = str;

    var methodName: String?
    if (histMat.containsKey(str))
        methodName = histMat.get(str)
    else
        methodName = conf.invokes[text];

    if (methodName == null) {
        for (e in conf.invokes.keys) {
            if (text.matches(Regex(e))) {
                text = e;
                methodName = conf.invokes[text];
                methodName?.let { MethodName2Ostr.put(it, e) }
                break;
            }
        }
    }

    methodName?.let {
        MethodName2Ostr?.let {
            if (MethodName2Ostr.containsKey(methodName))
                text = MethodName2Ostr.get(methodName).toString();
        }
    }

    methodName?.apply {
        invokes[this]?.apply {
            val r1 = Request(str, text, event.sender.id, event.subject.id, event, null)
            val res = this(PutGetter.get(event.sender.id, true), r1)
            res?.let {
                var resText = conf.invokesAfter[text]?.get(it.state)
                resText!!.apply {
                    if (this.trim().startsWith("[") && this.trim().endsWith("]")) {
                        val codeStr = this.substring(1, this.length - 1)
                        val me = MiraiCode.deserializeMiraiCode(codeStr)
                        event.subject.sendMessage(me)
                        return
                    }
                    res.returnArgs?.let {
                        var i = 1;
                        for (e in res.returnArgs!!) {
                            val tp1 = "$" + i++;
                            resText = resText?.replace(tp1, e.toString());
                        }
                    }
                    val mb = MessageChainBuilder()
                    for (e in toLink(resText!!)) {
                        parseType(e.toString(), event.subject, event.sender.id)?.let { it1 -> mb.append(it1) }
                    }
                    if (!mb.isEmpty())
                        event.subject.sendMessage(mb.build())
                }
            }
        }
        histMat.put(str, methodName)
    }
}

val eReg = Regex(",\\s+");

suspend fun parseType(resText: String, contact: Contact, id: Long): Message? {
    if (resText.startsWith("<") && resText.endsWith(">")) {
        val content = resText.substring(1, resText.length - 1)
        val ta = content.split(" = ")
        val type = ta[0]
        val args = ta[1].split(eReg)
        try {
            when {
                type == "MusicShare" -> {
                    return MusicShare(MusicKind.valueOf(args[0]), args[1], args[2], args[3], args[4], args[5])
                }
                type == "At" -> {
                    val t = args[0]
                    if (t.equals("?")) return At(id)
                    else return At(java.lang.Long.parseLong(t))
                }
                type == "Image" -> {
                    return createImageInGroup(contact, args[0])
                }
                type == "Face" -> {
                    return Face(Integer.parseInt(args[0]))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            err.println("${resText}\n解析失败")
        }
    } else return PlainText(resText)
    return null
}

val patt = Pattern.compile("<.*?>")
fun toLink(string: String): LinkedList<String> {
    var str = string
    var mat: Matcher = patt.matcher(str)
    val list = LinkedList<String>();
    while ((mat).find()) {
        val m2 = mat.group()
        val i1 = str.indexOf(m2)
        var m1: String = ""
        if (i1 == 0) {
            list.add(m2)
        } else {
            m1 = str.substring(0, i1)
            list.add(m1)
            list.add(m2)
        }
        str = str.substring(m1.length + m2.length)
        if (str.isNullOrEmpty()) break
        else
            mat = patt.matcher(str)
    }
    if (str.isNotEmpty()) list.add(str)
    return list
}