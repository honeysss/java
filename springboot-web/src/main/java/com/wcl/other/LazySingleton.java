package com.wcl.other;
//懒汉模式
//定义类
public class LazySingleton {
//    成员变量为static，没有初始化：类加载快，但访问类的唯一实例慢，static保证在自身类中获取自身对象
    private static LazySingleton ls = null;
//    构造函数定义为私有：不能在别的类中获取该类的对象，只能在本类中才可以得到该类的对象
    private LazySingleton () {

    }
//    getInstance方法为公开的，public为公开的，synchronized保证线程安全
    public synchronized static LazySingleton getInstance() {
        if (ls == null) {
            ls = new LazySingleton();
        }
        return ls;
    }
}
