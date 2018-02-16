package fsdf;

import org.junit.Test;

public class Tes {
    @Test
    public void Test(){
        String originStr = "abc";
        System.out.println(reverse(originStr));

    }
    public static String reverse(String originStr) {  // bc  abc
        if(originStr == null || originStr.length() <= 1)
            return originStr;  // c
         String aa =reverse(originStr.substring(1)); // c cb
                 char bb =originStr.charAt(0);  // b a
         return aa+bb;  // cb  cba
    }
}
