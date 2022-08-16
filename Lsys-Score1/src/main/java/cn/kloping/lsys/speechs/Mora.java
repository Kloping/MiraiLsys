package cn.kloping.lsys.speechs;


import static cn.kloping.lsys.utils.MessageUtils.random;

/**
 * @author github-kloping
 */

public enum Mora {
    a("石头"), b("剪刀"), c("布");

    private final String value_;

    Mora(String string) {
        this.value_ = string;
    }

    public static Mora findMora(String s, int i) {
        if (s.substring(i).contains(a.value_))
            return a;
        else if (s.substring(i).contains(b.value_))
            return b;
        else if (s.substring(i).contains(c.value_))
            return c;
        else
            return null;
    }

    public static Mora getRc() {
        int i = random.nextInt(3);
        switch (i) {
            case 0:
                return a;
            case 1:
                return b;
            default:
                return c;
        }
    }

    /**
     * @param r    赢得 概率
     * @param r2   平局的 概率
     * @param mora 对方的 出拳
     * @return
     */
    public static Mora getRc(int r, int r2, Mora mora) {
        int i = random.nextInt(100) + 1;
        if (i >= 90)
            System.out.print("");
        if (i < r) {
            return getWin(mora);
        } else if (i < r + r2)
            return mora;
        else return getLost(mora);
    }

    public static void main(String[] args) {
        int i = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        while (i++ < 10000)
            switch (getRc(10, 40, a).getValue()) {
                case "石头":
                    i1++;
                    continue;
                case "剪刀":
                    i2++;
                    continue;
                case "布":
                    i3++;
                    continue;
                default:
                    continue;
            }
        System.out.println(a.getValue() + ":\t" + i1);
        System.out.println(b.getValue() + ":\t" + i2);
        System.out.println(c.getValue() + ":\t\t" + i3);
    }

    public int Reff(Mora m) {
        if (this.value_.equals(m.value_)) {
            return 0;
        }
        if (m.getValue().equals("石头") && this.value_.equals("剪刀"))
            return -1;
        else if (m.getValue().equals("剪刀") && this.value_.equals("布"))
            return -1;
        else if (m.getValue().equals("布") && this.value_.equals("石头"))
            return -1;
        else
            return 1;
    }

    public String getValue() {
        return value_;
    }

    public static Mora getLost(Mora mora) {
        switch (mora) {
            case a:
                return c;
            case b:
                return a;
            case c:
                return b;
        }
        return null;
    }

    public static Mora getWin(Mora mora) {
        switch (mora) {
            case a:
                return b;
            case b:
                return c;
            case c:
                return a;
        }
        return null;
    }
}
