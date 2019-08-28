package org.ifunq.tanzongxi.jdk.map;

import java.util.HashMap;

public class HashMapTest03 {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put(new MyKey(), "a");
        hashMap.put(new MyKey(), "b");
        hashMap.put(new MyKey(), "c");
        hashMap.put(new MyKey(), "d");
        hashMap.put(new MyKey(), "e");
        hashMap.put(new MyKey(), "f");
        hashMap.put(new MyKey(), "g");
        hashMap.put(new MyKey(), "h");
        hashMap.put(new MyKey(), "i");
        hashMap.put(new MyKey(), "j");
        hashMap.put(new MyKey(), "k");
        hashMap.put(new MyKey(), "l");
        hashMap.put(new MyKey(), "m");
        hashMap.put(new MyKey(), "n");
    }

    static class MyKey {
        @Override
        public int hashCode() {
            return 0;
        }
    }

}
