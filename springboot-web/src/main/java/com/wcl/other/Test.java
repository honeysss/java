package com.wcl.other;

import org.thymeleaf.spring5.context.SpringContextUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {
//    public static void main(String[] args) {
//        Map<Integer,String> data = new HashMap<Integer,String >();
//        data.put(1,"小明");
//        data.put(2,"小明1");
//        data.put(3,"小明2");
//        data.put(4,"小明2");
//        System.out.println(data.values());
//        Test t = new Test();
//        Test t2 = new Test();
//    }
//    public Test() {
//        System.out.println("我是构造方法");
//    }
//静态块在类加载时只执行一次，代码块每次加载类都会被执行，无论创建多少个对象都会执行一次，构造方法也是每次创建对象的时候都会执行。
//    顺序 静态块>代码块>构造方法
//    static {
//        System.out.println("我是静态块");
//    }
//    {
//        System.out.println("我是代码块");
//    }
    public static void main(String[] args) {
//        System.out.println(getResult(6));
//        System.out.println(getFeiBo(7));



    }
    private void Test() {
        this.Test();
    }
//    某个数的阶乘   6的阶乘：1*2*3*4*5*6
    public static int getResult(int n) {
        if(n < 0) {
            System.out.println("非法参数");
        }
        if(n == 0 || n == 1) {
            return 1;
        }
        return getResult(n-1)*n;
    }

//斐波那契  1 1 2 3 5 8 13...
    public static int getFeiBo(int n) {
        if(n < 0) {
            return -1;
        }
        if(n == 1 || n == 2) {
            return 1;
        } else {
            return getFeiBo(n-1) + getFeiBo(n-2);
        }
    }


}
