package com.twjitm.threads;

/**
 * Created by �Ľ� on 2018/5/21.
 */
public class ThreadTest implements Runnable {
    @Override
    public void run() {
        System.out.println("���߳̿�ʼִ��");
        for (int i = 0; i < 10; i++) {
          /*  try {
               // Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(i);
        }
        System.out.println("���߳̽���ִ��");
    }

    public static void main(String[] args) {
        System.out.println("���߳̿�ʼ");
        ThreadTest threadTest = new ThreadTest();
        threadTest.run();
        Thread2 thread2 = new Thread2();
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("���߳̽���");
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " �߳����п�ʼ!");
            for (int i = 0; i < 5; i++) {
                System.out.println("���߳�" + Thread.currentThread().getName() + "���� : " + i);
                try {
                    sleep((int) Math.random() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " �߳����н���!");

        }
    }
}
