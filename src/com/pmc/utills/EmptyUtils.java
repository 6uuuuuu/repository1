package com.pmc.utills;

public class EmptyUtils {
    public static boolean isEmpty(String s){
        if (s == null ||"".equals(s)) {
            return true;
        }
        return false;
    }
}
