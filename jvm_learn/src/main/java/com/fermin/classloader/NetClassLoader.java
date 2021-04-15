package com.fermin.classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 从网络加载class
 */
public class NetClassLoader extends ClassLoader {

    //class文件的URL
    private String url;

    public NetClassLoader(String url) {
        this.url = url;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassDataFromNet(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 从网络获取class文件
     *
     * @param className
     * @return
     */
    private byte[] getClassDataFromNet(String className) {
        String path = classNameToPath(className);
        try {
            URL url = new URL(path);
            InputStream ins = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // 读取类文件的字节
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            //这里省略解密的过程.......
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        // class文件的全路径
        return url + "/" + className.replace('.', '/') + ".class";
    }

}