package com.nuc.finish.util;

import java.util.Random;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/8 21:39
 */
public class IdentifyCodeUtil {
    private static final Random random = new Random();
    private static Integer NUM_SIX = 6;

    public static String getRandomCode() {
        String res = "";
        for (int i = 0; i < NUM_SIX; i++) {
            int tmp = random.nextInt(10);
            String s = String.valueOf(tmp);
            res += s;
        }
        return res;
    }
}
