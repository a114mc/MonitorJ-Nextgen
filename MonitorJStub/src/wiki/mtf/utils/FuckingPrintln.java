package wiki.mtf.utils;

import wiki.mtf.Start;
public class FuckingPrintln {
    public static void out(Object shit){
        if(Start.shouldLog) System.out.println(shit);
    }
}
