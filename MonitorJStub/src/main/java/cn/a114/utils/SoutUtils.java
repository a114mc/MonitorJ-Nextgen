package cn.a114.utils;

import static cn.a114.utils.IANSIColor.ANSI_RESET;
import static cn.a114.utils.IANSIColor.ANSI_YELLOW;

public class SoutUtils {



    // https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    // Declaring ANSI_RESET so that we can reset the color

    public String yellowBGStr(String str){
        return(ANSI_YELLOW+str+ANSI_RESET);
    }
}
