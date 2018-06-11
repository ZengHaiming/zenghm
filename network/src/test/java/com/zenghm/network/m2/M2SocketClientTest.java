package com.zenghm.network.m2;


import org.junit.Test;



/**
 * Create date:18/6/9.
 * Created by: zhm.
 * Class name:M2SocketClientTest.
 */


public class M2SocketClientTest {
    @Test
    public void request() throws Exception {
       new  M2SocketClient("127.0.0.1",9778).request("My send msg !");
    }

}
