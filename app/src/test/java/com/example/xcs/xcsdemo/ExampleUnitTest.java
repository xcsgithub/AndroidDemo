package com.example.xcs.xcsdemo;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void hello(String... names){
        Observable.fromArray(names).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

     public String longestWord(String sen){
         String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_]";

         Pattern pat = Pattern.compile(regEx);
         Matcher mat = pat.matcher(sen);
         String[] senGroup = mat.replaceAll("").split(" ");
         String maxItem = senGroup[0];
         for (int i = 0; i< senGroup.length; i++){
             if (senGroup[i].length() > maxItem.length()){
                 maxItem = senGroup[i];
             }
         }
         return maxItem;
     }

     @Test
     public void getLongestWord(){
         System.out.print(longestWord("I love '''!you"));
     }

     public String[] reversStr(String str){
         String[] array = str.split(" ");
         List<String> list = Arrays.asList(array);
         Collections.reverse(list);
         return (String[]) list.toArray();
     }
     @Test
    public void revers(){
         System.out.print(Arrays.toString(reversStr("I Love You")));
     }

     @Test
    public void firstReverse() {

        // code goes here
    /* Note: In Java the return type of a function and the
       parameter types being passed are defined, so this return
       call must match the return type of the function.
       You are free to modify the return type. */
    String str = " I Love You";
        if(str == null || "".equals(str)){
            return ;
        }
        String newStr = "";
        char[] array = str.toCharArray();
        for(int i = array.length -1; i >=0; i--){
            newStr = newStr + array[i];
            newStr.toUpperCase();
        }
        System.out.print(newStr);

    }

    public static String LetterCapitalize(String str) {

        // code goes here
    /* Note: In Java the return type of a function and the
       parameter types being passed are defined, so this return
       call must match the return type of the function.
       You are free to modify the return type. */
        String[] array = str.split(" ");
        String newStr = "";
        for(int i = 0; i< array.length -1; i++){
            String stringI = array[i];
           char charI =  stringI.charAt(0);
            stringI.replace(stringI.charAt(0),String.valueOf(charI).toUpperCase().charAt(0));
            newStr += stringI;
        }

        return newStr;

    }

    @Test
    public void deleteListItem(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list){
            if ("2".equals(item)){
                list.remove(item);
            }
        }
        System.out.print(list.get(0));
    }
}