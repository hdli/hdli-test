package com.example.hdlitest.leetcode.huisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyi
 * @date 2023/5/11 11:15 PM
 */
public class Zuhe_CombinationSum_39 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        recursion(candidates,target,0);
        return result;
    }


    private void recursion2(int[] candidates, int target,int curSum,int startIndex){
        if (curSum > target){
            return;
        }
        if (target == curSum){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            int value = candidates[i];
            curSum += value;
            temp.add(value);
            //从当前位置之后获取包含本身可以在获取一次 所有下一层startIndex=i
            recursion2(candidates,target,curSum,i);
            temp.remove(temp.size()-1);
            curSum -= value;
        }

    }

    /**
     * 缺少startIndex ，需要额外增加checkSimilarity校验获取的数组是否已经存在
     * @param candidates
     * @param target
     * @param curSum
     */
    private void recursion(int[] candidates, int target,int curSum){
        if (curSum > target){
            return;
        }
        if (target == curSum){
            boolean b = checkSimilarity();
            if (b){
                result.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            int value = candidates[i];
            curSum += value;
            temp.add(value);
            recursion(candidates,target,curSum);
            temp.remove(temp.size()-1);
            curSum -= value;
        }

    }
    private boolean checkSimilarity(){
        if (result.isEmpty()){
            return true;
        }
        for (List<Integer> list : result){
            boolean b = checkTemp(list, temp);
            if (b){
                return false;
            }
        }
        return true;
    }

    /**
     * 校验两个集合的相似性
     * @param list
     * @param temp
     * @return
     */
    private boolean checkTemp(List<Integer> list,List<Integer> temp){
        if (list.size() != temp.size()){
            return false;
        }
        Map<Integer,Integer> map = new HashMap<>(list.size());
        for (Integer l:list){
            map.put(l,map.getOrDefault(l,0)+1);
        }
        for (Integer t:temp){
            map.put(t,map.getOrDefault(t,0)-1);
            if (map.get(t) < 0){
                return false;
            }
        }
        return true;
    }


}
