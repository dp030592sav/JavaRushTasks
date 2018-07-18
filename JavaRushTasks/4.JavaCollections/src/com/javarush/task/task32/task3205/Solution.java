package com.javarush.task.task32.task3205;

import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/*
Создание прокси-объекта
*/
public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        Class[] classes = new Class[] {SomeInterfaceWithMethods.class};
        SomeInterfaceWithMethods original = new SomeInterfaceWithMethodsImpl();
        InvocationHandler handler = new CustomInvocationHandler(original);

        SomeInterfaceWithMethods proxy
                = (SomeInterfaceWithMethods) Proxy.newProxyInstance(original.getClass().getClassLoader(), classes, handler);
        return proxy;
    }
}