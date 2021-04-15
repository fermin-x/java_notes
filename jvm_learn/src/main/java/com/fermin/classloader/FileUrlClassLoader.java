package com.fermin.classloader;

import java.io.File;
import java.net.*;

/**
 * 继承URLClassLoader实现自定义类加载器
 */
public class FileUrlClassLoader extends URLClassLoader {

    public FileUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public FileUrlClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }


    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        //定义文件路径
        String rootDir = "";
        File file = new File(rootDir);
        //File to URI
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};

        FileUrlClassLoader loader = new FileUrlClassLoader(urls);

        try {
            //加载指定的class文件
            Class<?> object1 = loader.loadClass("com.zejian.classloader.DemoObj");
            System.out.println(object1.newInstance().toString());

            //输出结果:I am DemoObj
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}