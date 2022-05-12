package cn.kloping.lsys.gamebase.conf

data class Game0Conf(
    var cd: Long = 3 * 60 * 60 * 1000L,
    var stopFrom: String = "23:00",
    var stopEnd: String = "5:00",
    var objs: String = "./conf/lSys/game0/objs.json",
) {
}
