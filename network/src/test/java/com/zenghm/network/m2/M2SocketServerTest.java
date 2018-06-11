package com.zenghm.network.m2;


import org.junit.Test;

/**
 * Create date:18/6/9.
 * Created by: zhm.
 * Class name:M2SocketServerTest.
 */


public class M2SocketServerTest {
    @Test
    public void start() throws Exception {
        new  M2SocketServer(9778).start();
    }

}
