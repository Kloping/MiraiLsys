package cn.kloping.lsys.sc1;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.savers.PutGetter;
import cn.kloping.lsys.utils.MessageUtils;
import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.number.NumberUtils;
import kotlin.jvm.functions.Function2;

import java.io.*;

import static cn.kloping.lsys.sc1.Sc1.conf;
import static cn.kloping.lsys.utils.MessageUtils.random;
import static cn.kloping.lsys.workers.Methods.*;

/**
 * @author github-kloping
 */
public class Methods {
    public static final Function2<User, Request, Result> ROB = (user, request) -> {
        long q2 = MessageUtils.getAtFromRequest(request);
        if (q2 == -1) return state1;
        User q2u = PutGetter.get(q2);
        if (q2u == null) return state2;
        if (user.getPf() >= 10) return state5;
        if (q2u.getP() >= 60) {
            if (user.getP() >= 60) {
                int r = random.nextInt(20) + 40;
                user.addP(r);
                user.addPf(1);
                q2u.addP(-r);
                user.apply();
                q2u.apply();
                return new Result(new Object[]{r}, 0);
            } else {
                return state4;
            }
        } else
            return state3;
    };
    public static final Function2<User, Request, Result> SIGN = (user, request) -> {
        int day = DateUtils.getDay();
        if (day != user.getUr()) {
            reg(day, user.getQq().longValue());
            int n = getRegNum(day);
            int s1 = conf.getSignNormalGet();
            switch (n) {
                case 1:
                    s1 = conf.getSignFirstGet();
                    break;
                case 2:
                    s1 = conf.getSignSecondGet();
                    break;
                case 3:
                    s1 = conf.getSignThirdGet();
                    break;
                default:break;
            }
            user.setPf(0);
            user.addP(s1);
            user.setUr(day);
            user.apply();
            return new Result(new Object[]{"http://q2.qlogo.cn/headimg_dl?dst_uin=" + user.getQq() + "&spec=100", n, s1}, 0);
        } else
            return state1;
    };
    public static final Function2<User, Request, Result> MORA_METHOD = (user, request) -> {
        String str = request.getStr();
        int num = 0;
        try {
            num = Integer.parseInt(NumberUtils.findNumberFromString(str));
            if (Mora.findMora(str, 0) == null) throw new Exception();
        } catch (Exception e) {
            return state5;
        }
        if (num < conf.getMoraMin() || num > conf.getMoraMax())
            return new Result(new Object[]{conf.getMoraMin(), conf.getMoraMax()}, 4);
        if (user.getP() < num)
            return state3;
        Mora m1 = Mora.findMora(str, 0);
        Mora m2 = Mora.getRc(conf.getWin(), conf.getOb(), m1);
        assert m1 != null;
        switch (m1.Reff(m2)) {
            case 0:
                return new Result(new Object[]{m2.getValue()}, 2);
            case -1:
                user.addP(-num);
                user.apply();
                return new Result(new Object[]{m2.getValue(), num}, 1);
            case 1:
                user.addP(num);
                user.apply();
                return new Result(new Object[]{m2.getValue(), num}, 0);
            default:
                throw new RuntimeException();
        }
    };

    public static int getRegNum(int day) {
        File file = new File(Resource.conf.getPath(), "regs/" + DateUtils.getYear() + "/" + DateUtils.getMonth());
        if (!file.exists()) file.mkdirs();
        file = new File(file, day + ".list");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] strings = FileUtils.getStringsFromFile(file.getPath());
        return strings.length;
    }

    public static void reg(int day, long q) {
        File file = new File(Resource.conf.getPath(), "regs/" + DateUtils.getYear() + "/" + DateUtils.getMonth());
        if (!file.exists()) file.mkdirs();
        file = new File(file, day + ".list");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file, true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.println(q + "");
        pw.flush();
        pw.close();
    }
}
