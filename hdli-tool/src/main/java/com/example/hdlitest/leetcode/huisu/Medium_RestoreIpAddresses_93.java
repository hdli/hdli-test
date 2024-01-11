package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/12 00:05
 */
public class Medium_RestoreIpAddresses_93 {

    List<String> temp = new ArrayList<>();
    List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        String a = "25525511135";
        Medium_RestoreIpAddresses_93 addresses93 = new Medium_RestoreIpAddresses_93();
        addresses93.restoreIpAddresses(a);
    }
    public List<String> restoreIpAddresses(String s) {
        if(s.length() > 12){
            return new ArrayList<>();
        }
        test(s,0);
        return result;
    }

    private void test(String s,int startIndex){
        if(temp.size() == 3){
            String value = s.substring(startIndex,s.length());
            if(check(value)){
                //有添加数据的地方，就要回溯
                temp.add(value);
                result.add(getStr(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        }
        for(int i = startIndex;i<s.length();i++){
            String code = s.substring(startIndex,i+1);
            if(!check(code)){
                break;
            }
            temp.add(code);
            test(s,i+1);
            temp.remove(temp.size()-1);
        }
    }

    private String getStr(List<String> temp){
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < temp.size();i++){
            String s = temp.get(i);
            if (i == temp.size() - 1){
                b.append(s);
            }else {
                b.append(s).append(".");
            }
        }

        return b.toString();
    }

    private boolean check(String a){
        if(a.equals("")){
            return false;
        }
        if(a.length() > 3){
            return false;
        }
        if (a.startsWith("0") && a.length() > 1){
            return false;
        }
        int integer = Integer.parseInt(a);
        return integer <= 255;
    }
}
