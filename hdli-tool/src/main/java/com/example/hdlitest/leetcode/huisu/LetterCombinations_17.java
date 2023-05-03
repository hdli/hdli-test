package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 * @author luyi
 * @date 2023/5/2 3:07 PM
 */
public class LetterCombinations_17 {
    static Map<Character,String> map = new HashMap();

    static {
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    String s = "";
    List<String> result = new ArrayList<>();


    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")){
            return new ArrayList<>();
        }
        t(digits,0);
        return result;
    }

    /**
     * digits = "23"   深度=digits的长度   返回 s的长度 = digits的长度
     *
     *
     *                                   s1=abc                        index=0
     *                       取a           取b              取c
     *                  s=a  s1=def     s=b  s1=def    s=c  s1=def     index=1
     *                取d   取e    取f
     *              s=ad   s=ae   s=af
     *
     *
     *
     *
     * @param digits
     * @param index   代表了需要遍历的集合如：digits=23 当 index = 0 是遍历 abc,当index = 1时遍历def
     */
    private void t(String digits,Integer index){
        if (index == digits.length()){
            result.add(new String(s));
            return;
        }
        //获取每一层要遍历的对象
        String s1 = map.get(digits.charAt(index));

        //字符串s的操作可以隐藏的，为了更好的理解回溯保留
        for (int i = 0; i < s1.length(); i++) {
            s = s+s1.charAt(i);
            t(digits,index+1);
            s = s.substring(0,s.length()-1);
            //t(digits,index+1,s+s1.charAt(i))
        }
    }

}
