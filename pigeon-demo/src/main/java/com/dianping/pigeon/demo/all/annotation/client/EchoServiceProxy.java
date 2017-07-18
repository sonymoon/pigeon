package com.dianping.pigeon.demo.all.annotation.client;

import com.dianping.pigeon.demo.api.EchoService;
import com.dianping.pigeon.remoting.invoker.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * Created by chenchongze on 17/6/22.
 */
@Component
public class EchoServiceProxy {

    @Reference(callType = "sync")
    private EchoService echoService;

    @Reference(callType = "future")
    private EchoService echoServiceFuture;

    @Reference(callType = "callback", callback = "com.dianping.pigeon.demo.all.annotation.client.EchoServiceCallback")
    private EchoService echoServiceCallback;

    public String remoteEcho(String msg) {
        // maybe do something
        return echoService.echo(msg);
    }

    public String localEcho(String msg) {
        // maybe do something
        return msg;
    }

    public EchoService getEchoService() {
        return echoService;
    }

    public EchoService getEchoServiceFuture() {
        return echoServiceFuture;
    }

    public EchoService getEchoServiceCallback() {
        return echoServiceCallback;
    }
}
