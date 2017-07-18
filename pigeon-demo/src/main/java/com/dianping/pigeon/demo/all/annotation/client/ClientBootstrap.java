package com.dianping.pigeon.demo.all.annotation.client;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.dianping.pigeon.demo.api.EchoService;
import com.dianping.pigeon.remoting.common.config.annotation.PigeonConfiguration;
import com.dianping.pigeon.remoting.invoker.concurrent.FutureFactory;
import com.dianping.pigeon.remoting.invoker.concurrent.InvocationCallback;
import com.dianping.pigeon.remoting.invoker.util.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by chenchongze on 17/6/22.
 */
@PigeonConfiguration(basePackages = "com.dianping.pigeon.demo.all.annotation.client")
public class ClientBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ClientBootstrap.class);
    private static AnnotationConfigApplicationContext ctx;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        initContext();
        EchoServiceProxy echoServiceProxy = ctx.getBean("echoServiceProxy", EchoServiceProxy.class);

        testEchoBasic(echoServiceProxy);

//        testCallback(echoServiceProxy);

        System.in.read();
    }

    private static void testCallback(EchoServiceProxy echoServiceProxy) {
        EchoService echoServiceCallback = echoServiceProxy.getEchoServiceCallback();
        echoServiceCallback.echo("test callback");

        InvokerHelper.setCallback(new InvocationCallback() {
            @Override
            public void onSuccess(Object result) {
                logger.info("dynamic callback: " + result);
            }

            @Override
            public void onFailure(Throwable exception) {
                logger.error("dynamic callback: " + exception);
            }
        });

        echoServiceCallback.echo("ccz");
    }

    private static void testEchoBasic(EchoServiceProxy echoServiceProxy) {
        logger.info(echoServiceProxy.localEcho("hello world")); // 本地方法调用
        logger.info(echoServiceProxy.remoteEcho("hello pigeon")); // pigeon远程调用
    }

    private static void initContext() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(ClientBootstrap.class);
        ctx.refresh();
    }

    private static void testPrintCat(EchoService echoServiceFuture) throws ExecutionException, InterruptedException {
        Transaction t = Cat.newTransaction("test","test");

        echoServiceFuture.echo("111");
        Future future1 = FutureFactory.getFuture();
        echoServiceFuture.echo("222");
        Future future2 = FutureFactory.getFuture();

        future1.get();
        future2.get();

        System.out.println(Cat.getManager().getThreadLocalMessageTree());
        t.complete();
    }
}
