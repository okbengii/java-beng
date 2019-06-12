package com.beng.enums;

/**
 * 枚举类
 * 
 * @author apple
 */
public enum Constant {

    MON("星期一"), TUE("星期二"), WED("星期三"), THU("星期四"), FRI("星期五"), SAT("星期六"), SUN("星期日");

    private String desc;

    private Constant(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean containKey(String key) {
        Constant[] cons = Constant.values();
        for (Constant con : cons) {
            if (con.name().equals(key))
                return true;
        }
        return false;
    }

}
