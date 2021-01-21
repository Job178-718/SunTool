package util;

import java.util.Random;

public class Get {
    /**
     * 获取随机数的工具类
     * @param n
     * @return
     */
    public static int random(int n){
        double random = Math.random()*n;
        int m = (int)random;
        return m;
    }
}
