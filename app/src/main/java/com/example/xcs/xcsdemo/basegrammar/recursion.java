package com.example.xcs.xcsdemo.basegrammar;

import com.example.xcs.xcsdemo.Util.LogUtil;

/**
 * @author RWX
 * @time 2019-04-25.
 */
public class recursion {

   public static void main(String[] args){
       System.out.print(add(5)+"");
   }

   public static int add(int n){
       if (n == 1){
           return 1;
       }else {
           return n + add(n -1);
       }
   }
}
