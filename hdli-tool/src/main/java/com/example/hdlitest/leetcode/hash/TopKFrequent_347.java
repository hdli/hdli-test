package com.example.hdlitest.leetcode.hash;

import java.util.*;
import java.util.stream.Collectors;


/**
 *  前 K 个高频元素
 *
 * Comparator接口说明: 返回负数，形参中第一个参数排在前面；返回正数，形参中第二个参数排在前面
 *
 * 对于队列：排在前面意味着往队头靠
 * 对于堆（使用PriorityQueue实现）：从队头到队尾按从小到大排就是最小堆（小顶堆），
 *                              从队头到队尾按从大到小排就是最大堆（大顶堆）--->队头元素相当于堆的根节点
 *
 * @author luyi
 * @date 2023/7/16 15:03
 */
public class TopKFrequent_347 {

    /**
     *
     * 解法1：基于大顶堆实现   从大到小排
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i, 0)+1);
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是 从大到小排 ,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair2[1]-pair1[1]);
        //大顶堆需要对所有元素进行排序
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            pq.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] ans = new int[k];
        //依次从队头弹出k个,就是出现频率前k高的元素
        for(int i=0;i<k;i++){
            ans[i] = pq.poll()[0];
        }
        return ans;
    }

    /**
     *
     * 解法2：基于小顶堆实现   从小到大排
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i, 0)+1);
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是 从小到大排 ,出现次数最低的在队头(相当于小顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair1[1]-pair2[1]);
        //小顶堆只需要维持k个元素有序
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            if(pq.size() < k){
                //小顶堆元素个数小于k个时直接加
                pq.add(new int[]{entry.getKey(),entry.getValue()});
            }else {
                //当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                if(entry.getValue()>pq.peek()[1]){
                    //弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.poll();
                    pq.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] ans = new int[k];
        //依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
        for(int i=0;i<k;i++){
            ans[i] = pq.poll()[0];
        }
        return ans;
    }


    /**
     * 桶排序法
     * <p>
     * 首先依旧使用哈希表统计频率，统计完成后，创建一个数组，将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标即可
     *
     * @param nums
     * @param k
     * @return
     */
    public Object[] topKFrequent3(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i, 0)+1);
        }
        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length+1];
        for(int key : map.keySet()){
            Integer i = map.get(key);
            if (list[i] == null){
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }
        List<Integer> res = new ArrayList();
        // 倒序遍历数组获取出现顺序从大到小的排列
        for(int i = list.length - 1;i >= 0 && res.size() < k;i--){
            if(list[i] == null) {
                continue;
            }
            res.addAll(list[i]);
        }
        return res.toArray();
    }


    /**
     * 使用java自带的排序，不合规矩
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent4(int[] nums, int k){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:nums){
            map.put(i,map.getOrDefault(i, 0)+1);
        }
        List<Integer> collect = map.entrySet().stream().sorted((a, b) -> {
            if (a.getValue() >= b.getValue()) {
                return -1;
            } else {
                return 1;
            }
        }).map(Map.Entry::getKey).collect(Collectors.toList());
        int [] res = new int[k];
        for (int i = 0;i<k;i++){
            res[i] = collect.get(i);
        }
        return res;
    }

}
