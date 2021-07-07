package com.wcl.other;
//饿汉模式
//创建类
public class HungrySingleton {
//  静态私有成员，在类被加载的时候就已经初始化了。加载类的时候慢，但是获取对象速度快
    private static HungrySingleton hs = new HungrySingleton();
//    私有构造函数
    private HungrySingleton() {

    }
//    公开访问方法，不需要synchronized,因为类在被加载的时候已经被初始化了
    public static HungrySingleton getInstance() {
        return hs;
    }
}
