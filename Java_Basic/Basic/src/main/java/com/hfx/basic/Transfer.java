package com.hfx.basic;

/**
 * 基本类型之间的转换
 */
public class Transfer {
    public static void main(String[] args) {
        // 字符串与整形之间的转换
        String s = "1234";
        int a = Integer.valueOf(s);
        int b = a * 2;
        String s2 = String.valueOf(b);

        int c = Integer.parseInt(s2);
        System.out.println(a);
        System.out.println(c);

    }
}
