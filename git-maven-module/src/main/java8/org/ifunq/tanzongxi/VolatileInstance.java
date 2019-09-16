package org.ifunq.tanzongxi;

public class VolatileInstance {
    volatile VolatileInstance instance;

    public final String finalValue = "abc";

    boolean flag = false;

    public VolatileInstance getInstance() {
        synchronized (this) {
            if (instance == null) {
                instance = new VolatileInstance();
            }
        }
        return instance;
    }

    int count = 0;

    public void chongPaixu() {
        setFlag();
        count = 9;
    }


    public void setFlag() {
        flag = true;
    }

    public int calCount() {
        while (true) {
            if (flag) {
                return count * count;
            }
        }
    }
}
 class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1;    // 1写final域
        obj = this;    // 2 this引用在此"逸出"
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        if (obj != null) {    // 3
            int temp = obj.i;    // 4
        }
    }
}







