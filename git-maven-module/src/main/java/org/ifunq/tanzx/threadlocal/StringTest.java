package org.ifunq.tanzx.threadlocal;

/**
 * Created by tanzx on 2017/4/16.
 */
public class StringTest {
    public String str = "abc";

    private ThreadLocal<String> strTd = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return str;
        }
    };

    public String getStr() {
        return strTd.get();
    }

    public static void main(String[] args) {
        StringTest strTest = new StringTest();
        StringThread thread01 = new StringThread(strTest);
        StringThread thread02 = new StringThread(strTest);
        StringThread thread03 = new StringThread(strTest);
        thread01.start();
        thread02.start();
        thread03.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        strTest.str = "efg";
    }

}
class StringThread extends Thread {
    StringTest strTest;
    public StringThread(StringTest strTest) {
        this.strTest = strTest;
    }

    @Override
    public void run() {
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   Num:" + strTest.getStr());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadName:" + Thread.currentThread().getName() + "   Num:" + strTest.getStr());

    }
}