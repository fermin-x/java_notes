package com.fermin.spi;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过SPI机制对第三方服务的加载
 *
 * @param <T>
 */
public abstract class AbstractThirdService<T extends BaseThirdService> {

    private T service = null;

    private AtomicBoolean initFlag = new AtomicBoolean(false);

    private Map<String, T> serviceMap = new HashMap<>(4);

    public void load() {
        if (initFlag.compareAndSet(false, true)) {
            // 返回直接继承的父类（包含泛型参数）,并返回泛型的第一个参数类型,实际上是想获取ShoutService
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            load(entityClass);
        }
    }

    public void load(Class<T> clazz) {
        ServiceLoader<T> serviceList = ServiceLoader.load(clazz);
        for (T service : serviceList) {
            serviceMap.put(service.getCode(), service);
        }
    }

    public T getService(String code) {
        load();
        return serviceMap.get(code);
    }
}