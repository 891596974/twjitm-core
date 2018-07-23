package com.twjitm.threads;

/**
 *
 * Obj.wait()����Obj.notify()����Ҫ��synchronized(Obj)һ��ʹ�ã�Ҳ����wait,��notify������Ѿ���ȡ��Obj�����в�����
 * ���﷨�Ƕ���˵����Obj.wait(),Obj.notify������synchronized(Obj){...}�����ڡ��ӹ�������˵wait����˵�߳��ڻ�ȡ��������
 * �����ͷŶ�������ͬʱ���߳����ߡ�ֱ���������̵߳��ö����notify()���Ѹ��̣߳����ܼ�����ȡ��������������ִ�С�
 * ��Ӧ��notify()���ǶԶ������Ļ��Ѳ���������һ����Ҫע�����notify()���ú󣬲��������Ͼ��ͷŶ������ģ�
 * ��������Ӧ��synchronized(){}����ִ�н������Զ��ͷ�����JVM����wait()���������߳������ѡȡһ�̣߳��������������
 * �����̣߳�����ִ�С��������ṩ�����̼߳�ͬ�������ѵĲ�����
 * Thread.sleep()��Object.wait()���߶�������ͣ��ǰ�̣߳��ͷ�CPU����Ȩ����Ҫ����������Object.wait()���ͷ�CPUͬʱ���ͷ��˶������Ŀ��ơ�
 */
public class InputThreadTest implements Runnable {
    String name;
    Object prev;
    Object self;

    public InputThreadTest(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int cout = 0;
        while (cout < 10) {
            synchronized (prev) {
                synchronized (self) {
                    cout++;
                    System.out.print(name);
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        InputThreadTest threadTesta = new InputThreadTest("A", c, a);
        InputThreadTest threadTestb = new InputThreadTest("B", a, b);
        InputThreadTest threadTestc = new InputThreadTest("C", b, c);
        new Thread(threadTesta).start();
        Thread.sleep(100);
        new Thread(threadTestb).start();
        Thread.sleep(100);
        new Thread(threadTestc).start();

    }
}
