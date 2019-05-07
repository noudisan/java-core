package com.ztt.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) { //如果当前队列内的个数等于最大长度,那么释放锁.
                notFull.await();
            }
            if (++addIndex == items.length) { //如果已经到了尾部,那么从头开始.
                addIndex = 0;
            }
            ++count;
            notEmpty.signal(); //通知阻塞在"空"条件上的线程.
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); //如果当前队列的个数等于0,那么释放锁.
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal(); //通知阻塞在"满"条件上的线程.
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}
