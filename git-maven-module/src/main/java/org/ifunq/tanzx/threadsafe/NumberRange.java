package org.ifunq.tanzx.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tanzx on 2017/4/18.
 */
public class NumberRange {
    private AtomicInteger lower = new AtomicInteger(0);
    private AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException();
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException();
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i <= upper.get() && i >= lower.get());
    }
}
