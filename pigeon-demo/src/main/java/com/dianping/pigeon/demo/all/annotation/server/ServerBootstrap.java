package com.dianping.pigeon.demo.all.annotation.server;

import com.dianping.pigeon.remoting.common.config.annotation.PigeonConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * Created by chenchongze on 17/6/22.
 */
@PigeonConfiguration(basePackages = "com.dianping.pigeon.demo.all.annotation.server")
public class ServerBootstrap {

    private static final Logger logger = LoggerFactory.getLogger(ServerBootstrap.class);

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ServerBootstrap.class);
        ctx.refresh();

        logger.info("pigeon-demo server start");
        System.in.read();
    }
}
