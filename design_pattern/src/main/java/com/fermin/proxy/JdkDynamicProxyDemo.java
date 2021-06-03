package com.fermin.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IUserDao {
    public void save(String name);
}

public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        IUserDao proxyInstance = (IUserDao) new ProxyFactory(userDao).getProxyInstance();
        proxyInstance.save("tom");

    }
}

class ProxyFactory {
    // maintain a target object
    private Object target;

    public ProxyFactory(Object obj) {
        this.target = obj;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("open a transaction ");

                Object result = method.invoke(proxy, args);

                System.out.println("commit ");
                return result;
            }
        });
    }
}

class UserDaoImpl implements IUserDao {
    @Override
    public void save(String name) {
        System.out.print("save data:" + name);
    }
}
