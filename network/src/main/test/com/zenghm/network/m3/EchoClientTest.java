package com.zenghm.network.m3;

import org.junit.Test;



/**
 * Create date:18/6/3.
 * Created by: zhm.
 * Class name:EchoClientTest.
 */
public class EchoClientTest {
    @Test
    public void main() throws Exception {
        EchoClient.main(new String[]{"127.0.0.1","7112"});
    }

}
