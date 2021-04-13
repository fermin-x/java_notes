package com.fermin.classload;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 类加载器
 * bootStrap ClassLoader
 * <p>
 * Extend
 */
public class ClassLoaderLearn {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader().getParent();
        URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
        URL[] urls = urlClassLoader.getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
    }
}
