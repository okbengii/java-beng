package com.beng.suanfa;

/**
 * @author apple
 * @desc 压缩字符串
 */

public class Solution2 {

    public static void main(String[] args) {
        System.out.println(me("aaaabbbccc"));
    }

    public static String me(String str) {

        StringBuffer stringBuffer = new StringBuffer();
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                stringBuffer.append(last);
                if (count > 1) {
                    stringBuffer.append(count);
                }
                last = str.charAt(i);
                count = 1;
            }
        }
        if (count > 1)

        {
            stringBuffer.append(count);
        }
        stringBuffer.append(last);
        String string = stringBuffer.toString();
        return string;
    }
}
