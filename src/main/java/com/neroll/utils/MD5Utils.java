package com.neroll.utils;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    public static String stringToMD5(String string) {
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }
}
