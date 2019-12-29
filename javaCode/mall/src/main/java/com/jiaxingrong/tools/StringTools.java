package com.jiaxingrong.tools;

import com.jiaxingrong.execption.InputException;

/**
 * @Author:luchang
 * @Date: 2019/12/27 16:02
 * @Version 1.0
 */
public class StringTools {

    private static int STR_LENGTH = 30;

    public static boolean isNull(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        }
        return false;
    }

    public static void strLengthGt(String string) throws InputException {
        if (string.length() > STR_LENGTH) {
            throw new InputException("输入数据过长，请保证长度小于30");
        }
    }

    public static boolean isNum (String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
        }
        return true;
    }
}
