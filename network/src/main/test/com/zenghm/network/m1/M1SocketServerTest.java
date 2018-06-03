package com.zenghm.network.m1;

import org.junit.Test;

/**
 * Create date:18/6/2.
 * Created by: zhm.
 * Class name:M1SocketServerTest.
 */

public class M1SocketServerTest {
    @Test
    public void startListen() throws Exception {
        M1SocketServer server = new M1SocketServer(9778);
        server.startListen();
    }

}
