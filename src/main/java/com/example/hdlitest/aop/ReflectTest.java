package com.example.hdlitest.aop;

import com.example.hdlitest.aop.impl.NormalCalculator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-2 11:18
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        System.out.println("#####################获取反射中的class对象");
        //1.当你知道该类的全路径名时，你可以使用该方法获取 Class 类对象
        Class<?> aClass = Class.forName("com.example.hdlitest.aop.impl.NormalCalculator");
        //2.这种方法只适合在编译前就知道操作的 Class
        Class clz = String.class;
        //3.使用类对象的 getClass() 方法
        String str = new String("Hello");
        Class clz1 = str.getClass();

        System.out.println("#####################通过反射创建类对象");
        //1.通过 Class 对象的 newInstance() 方法（只能使用默认的无参数构造方法）
        NormalCalculator o = (NormalCalculator) aClass.newInstance();
        //2.通过 Constructor 对象的 newInstance() 方法（选择特定构造方法）
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        NormalCalculator o1 = (NormalCalculator)constructor1.newInstance("保留字段");

        System.out.println("#####################通过反射获取类属性、方法、构造器");
        //1.通过 Class 对象的 getFields() 方法可以获取 Class 类的属性，但无法获取私有属性
        Field[] fields = aClass.getFields();
        for (Field f :fields) {
            System.out.println(f.getName());
        }
        //2.getDeclaredFields() 方法则可以获取包括私有属性在内的所有属性：
        Field[] fields2 = aClass.getDeclaredFields();
        for (Field f : fields2) {
            System.out.println(f.getName());
        }
        //与获取类属性一样，当我们去获取类方法、类构造器时，如果要获取私有方法或私有构造器，则必须使用有 declared 关键字的方法

        String name = aClass.getName();
        Annotation[] annotations = aClass.getAnnotations();
        System.out.println(name +"注解有"+annotations.length);

        Constructor<?> constructor = aClass.getConstructor();
        Object instance = constructor.newInstance();
        Method add = aClass.getMethod("add", Integer.class,Integer.class);
        Object addResult = add.invoke(instance, 1, 1);
        System.out.println(addResult);


    }
}
