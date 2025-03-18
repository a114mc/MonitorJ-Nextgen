package cn.a114.utils;

import cn.a114.Start;
public class ClientPrinter {
    public static void out(Object shit){

        if(Start.shouldLog) {
            System.out.println(shit);
        }
    }
}
