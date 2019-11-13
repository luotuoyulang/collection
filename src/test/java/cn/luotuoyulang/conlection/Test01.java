package cn.luotuoyulang.conlection;

public class Test01 {

    public static void main(String[] args) {


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(199999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                readFile();
            }
        };
        thread1.start();
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                writeFile();
            }
        };
        thread2.start();

    }

    public static void readFile() {
        System.err.println("开始读数据。。。");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("读数据完毕。。。");
    }

    public static void writeFile() {
        System.err.println("开始写数据。。。。");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("写数据结束。。。。");
    }
}