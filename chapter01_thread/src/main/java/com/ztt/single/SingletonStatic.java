package com.ztt.single;

/**
 * 优点：避免了线程不安全，延迟加载，效率高。
 */
public class SingletonStatic {

    private SingletonStatic() {
    }

    private static class SingletonInstance {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
