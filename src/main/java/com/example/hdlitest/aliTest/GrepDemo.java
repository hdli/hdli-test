package com.example.hdlitest.aliTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author thliu@credit2go.cn
 * @date 2019/6/1 15:03
 */
public class GrepDemo {

    // 定长队列
    class LimitedQueue<E> extends LinkedList<E> {
        private static final long serialVersionUID = 1L;
        private int limit;

        public LimitedQueue(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean add(E o) {
            super.add(o);
            while (size() > limit) { super.remove(); }
            return true;
        }
    }

    class GrepString implements Serializable {
        private Integer lineNumber;
        private Queue bufferQueue;

        public GrepString (Integer lineNumber, Queue bufferQueue) {
            // 深拷贝
            Queue newQueue = new LimitedQueue(11);
            newQueue.addAll(bufferQueue);
            this.lineNumber = lineNumber;
            this.bufferQueue = newQueue;
        }


        public Integer getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(Integer lineNumber) {
            this.lineNumber = lineNumber;
        }

        public Queue getBufferQueue() {
            return bufferQueue;
        }

        public void setBufferQueue(Queue bufferQueue) {
            this.bufferQueue = bufferQueue;
        }
    }


    public void grep(String path, String query) throws IOException {
        // 实现sort 排序
        Map sortMap = new TreeMap<String, GrepString>();
        // 实现uniq -c 重复行统计
        Map repeatMap = new HashMap<String, Integer>();
        // 固定队列 实现head -n10
        Queue<String> bufferQueue = new LimitedQueue<String>(11);
        // 行号
        int lineNumber = 0;
        String line = null;

        FileReader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);

        line = br.readLine();
        while (line != null) {
            // 放入队列中
            bufferQueue.add(line);
            lineNumber++;
            if (line.indexOf(query) != -1) {
                // 记录重复数
                if (repeatMap.containsKey(line)) {
                    repeatMap.put(line, (Integer)repeatMap.get(line) + 1);
                } else {
                    repeatMap.put(line, 1);
                }
                // 记录行号和grep行上面10行数据(算本身一共11行)
                // <line + Integer.toString(lineNumber)>避免重复
                sortMap.put(line + Integer.toString(lineNumber), new GrepString(lineNumber, bufferQueue));
            }
            line = br.readLine();
        }

        // 实现sort -r排序
        sortMap = ((TreeMap) sortMap).descendingMap();

        Iterator<Map.Entry<String, GrepString>> entries = sortMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, GrepString> entry = entries.next();
            GrepString grepString = entry.getValue();
            Queue queue = grepString.getBufferQueue();
            // 这里写打印流程
            while (queue.peek() != null) {
                System.out.println(queue.poll());
            }
            // 打印行号
            System.out.println("lineNumber--------------" + grepString.getLineNumber());
            Integer replaceLength = grepString.getLineNumber().toString().length();
            // 打印重复数 需要截取之前避免重复的行号
            System.out.println("repeatNumber------------" + repeatMap.get(entry.getKey().substring(0, entry.getKey().length() - replaceLength)));

        }


    }


    public static void main(String[] args) throws IOException {
        String path = "E:\\opt\\app\\log\\fitness_log.log";
        String pattern = "Exception";
        new GrepDemo().grep(path, pattern);
    }

}