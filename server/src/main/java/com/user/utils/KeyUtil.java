package com.user.utils;

import java.util.Random;

/**
 * @Auther: shuaihu.shen@hand-china.com
 * @Date: 2018/10/11 14:42
 * @Description:
 */
public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }

    public static void main(String[] args) {
        System.out.println( KeyUtil.genUniqueKey());
    }
}
