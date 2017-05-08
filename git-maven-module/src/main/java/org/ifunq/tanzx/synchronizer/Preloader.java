package org.ifunq.tanzx.synchronizer;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by tanzx on 2017/5/8.
 */
public class Preloader {
    Future<Object> fu = new FutureTask<Object>(new Callable<Object>() {
        public Object call() throws Exception {
            return null;
        }
    });

}
