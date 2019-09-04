package org.ifunq.tanzongxi.jdk.lock;

public class JavaGotoTest {

    public static void main(String[] args) {
        boolean flag = true;
        goto01:
        for (int k = 1; k <= 1; k++) {
            for (int j = 1; j <= 1; j++) {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                    if (i == 5 && flag) {
                        flag = false;
                        continue goto01;
                    }
                }
            }
        }
    }
}
