package com.liyuxin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyProxy proxy = new MyProxy();
        proxy.publish(new DoubleSixImpl());
    }
}
