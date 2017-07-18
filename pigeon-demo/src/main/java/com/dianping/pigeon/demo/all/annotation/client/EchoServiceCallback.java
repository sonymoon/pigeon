package com.dianping.pigeon.demo.all.annotation.client;

import com.dianping.pigeon.remoting.invoker.concurrent.InvocationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenchongze on 17/7/6.
 */
public class EchoServiceCallback implements InvocationCallback {

    private final Logger logger = LoggerFactory.getLogger(EchoServiceCallback.class);

    @Override
    public void onSuccess(Object result) {
        logger.info("callback: " + result);
    }

    @Override
    public void onFailure(Throwable exception) {
        logger.error(exception.toString());
    }
}
