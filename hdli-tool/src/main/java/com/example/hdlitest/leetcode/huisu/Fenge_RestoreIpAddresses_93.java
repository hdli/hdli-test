package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyi
 * @date 2024/1/12 00:05
 */
public class Fenge_RestoreIpAddresses_93 {

    List<String> temp = new ArrayList<>();
    List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        String a = "25525511135";
        Fenge_RestoreIpAddresses_93 addresses93 = new Fenge_RestoreIpAddresses_93();
        addresses93.restoreIpAddresses(a);
    }

    /**
     *  复原 IP 地址
     *  有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * @param s
     * @return
     */
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
