package com.zenghm.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Create date:2018/05/13.
 * Created by: Airlen.
 * Class name:ZSocket.
 */
public class ZSocketClient {
    public void test() throws IOException {
        Socket socket = new Socket("jenkov.com",80);
        OutputStream out = socket.getOutputStream();

    }
}
