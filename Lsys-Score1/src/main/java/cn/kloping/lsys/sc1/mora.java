package cn.kloping.lsys.sc1;


import static cn.kloping.lsys.utils.MessageUtils.random;

public enum mora {
    a("石头"), b("剪刀"), c("布");

    private final String value_;

    mora(String string) {
        this.value_ = string;
    }

    public static mora findMora(String s, int i) {
        if (s.substring(i).contains(a.value_))
            return a;
        else if (s.substring(i).contains(b.value_))
            return b;
        else if (s.substring(i).contains(c.value_))
            return c;
        else
            return null;
    }

    public static mora getRc() {
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

    public int Reff(mora m) {
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
}
