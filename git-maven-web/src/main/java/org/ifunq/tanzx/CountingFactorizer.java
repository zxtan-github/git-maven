package org.ifunq.tanzx;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by tanzx on 2017/4/18.
 */
public abstract class CountingFactorizer implements Servlet {
    private final AtomicLong count;

    {
        count = new AtomicLong(0);
    }

    public long getCount() { return count.get(); }

    public void service(ServletRequest req, ServletResponse resp) {
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = factor(i);
//        count.incrementAndGet();
//        encodeIntoResponse(resp, factors);
    }
}