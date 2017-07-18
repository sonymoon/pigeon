package com.dianping.pigeon.demo.all.annotation.server;

import com.dianping.pigeon.demo.api.EchoService;
import com.dianping.pigeon.remoting.provider.config.annotation.Service;

/**
 * Created by chenchongze on 17/6/22.
 */
@Service
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String msg) {
        return msg;
    }
}
