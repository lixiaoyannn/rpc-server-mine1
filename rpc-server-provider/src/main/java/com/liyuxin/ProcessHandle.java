package com.liyuxin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 创建者:小䶮
 */
public class ProcessHandle implements Runnable {
    private Socket socket;
    private Object service;

    public ProcessHandle(Socket socket,Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RequestEntry requestEntry = (RequestEntry)objectInputStream.readObject();
            Object returnStr = invoke(requestEntry);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(returnStr);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RequestEntry requestEntry) throws Exception{
        String className = requestEntry.getClassName();
        Object[] params = requestEntry.getArgs();
        Class<?>[] paramTypes = new Class<?>[params.length];
        for(int i=0;i<params.length;i++){
            Object temp = params[i];
            Class<?> clazz = temp.getClass();
            paramTypes[i] = clazz;
        }
        Method method = Class.forName(requestEntry.getClassName()).getMethod(requestEntry.getMethodName(),paramTypes);

        return (Object) method.invoke(service,params);
    }
}
