package com.liyuxin;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyProxy {
    ExecutorService executorService = Executors.newCachedThreadPool();
    //发布服务
    public void publish(Object service){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            executorService.execute(new ProcessHandle(socket,service));
        }catch(Exception e){

        }
    }
}
