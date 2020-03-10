package com.example.hdlitest.lambda;


import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-4-10 21:16
 */
public class LambdaTest {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("20190910");
        list.add("20190829");
        list.add("20190827");
        list.add("20190826");
        list.add("20190824");
        list.add("20190827");

        list.sort((o1,o2) -> o1.compareTo(o2));

        list.forEach(System.out::println);


    }

    private void sortUsingJava8(){
        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");
        Collections.sort(names2, (s1, s2) -> s1.compareTo(s2));
    }

    //相当于 java 7中的
    private void sortUsingJava7(List<String> names){
        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");
        Collections.sort(names2, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    private static Runnable test(){
       return ()-> {
           System.out.println(Thread.currentThread().getName()+"开始执行任务");
           System.out.println(new Date());
       };
    }

    //---------------------Stream
    private static void sortTest(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("f");
        list.add("e");

        list.stream().sorted((a,b)->a.compareTo(b)).forEach(System.out::println);
//        list.stream().sorted(String::compareTo).forEach(System.out::println);
    }

    /**
     * 过滤  filter方法执行后得到的“子集合”可以比原集合的size小
     */
    private static void filterTest(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.parallelStream().filter(t -> t.equals("a")).forEach(System.out::println);
        list.forEach(System.out::println);
    }

    /**
     * 转换  map的意思是，1对1转换 也就是说map方法执行后，得到的“新集合”跟原集合的size是一样大的
     */
    private static void mapTest(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 合并
     */
    private static void flatMapTest(){
        List<Integer> collected0 = new ArrayList<>();
        collected0.add(1);
        collected0.add(3);
        collected0.add(5);
        List<Integer> collected1 = new ArrayList<>();
        collected1.add(2);
        collected1.add(4);
        collected1 = Stream.of(collected0, collected1)
                .flatMap(num -> num.stream()).collect(Collectors.toList());

    }

    /**
     * 算和
     */
    private static void reduceTest(){
        List<Integer> collected0 = new ArrayList<>();
        collected0.add(1);
        collected0.add(3);
        collected0.add(5);
        int sumAll = collected0.stream().reduce(0,(sum,element)-> sum+element);
        System.out.println(sumAll);

//        int sumAll = collected0.stream().reduce(0,Integer::sum);
        //  Integer在Java8中提供了sum求和静态方法。
    }

    //Stream可以链式调用，可以把链条拉得很长。比如，我们要获取，年龄在加上5岁后仍然小于8岁的男性,的姓氏。我们可以用类似下面的代码来实现

    private static void streamTest(){
//        List<Person> personList = new PersonFactory().getPersons(8);//随机获取8个person实例
//        List<String> names = new ArrayList<>();
//        personList.stream().map(person -> {
//            person.setAge(person.getAge() + 5);//年龄加5岁
//            return person;
//        }).filter(person -> person.getAge() < 8)//年龄小于8
//                .filter(person -> person.getGen().equals("男"))//男性
//                .forEach(person -> names.add(person.getFirstName()));//获取姓氏
//
//        System.out.println(names);
    }


    private static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\opt\\app\\log\\fitness_log.log"));
        int longest = br.lines().mapToInt(String::length).max().getAsInt();
        br.close();
        System.out.println(longest);
    }
}

