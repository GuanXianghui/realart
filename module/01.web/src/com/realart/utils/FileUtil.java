package com.realart.utils;

import java.io.*;

/**
 * 文件工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-1 11:33
 */
public class FileUtil {
    /**
     * 缓存大小
     */
    private static final int BUFFER_SIZE = 1444;

    /**
     * 拷贝文件
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst) {
        try {
            int byteRead;
            if (src.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(src); //读入原文件
                FileOutputStream fs = new FileOutputStream(dst);
                byte[] buffer = new byte[BUFFER_SIZE];
                while ( (byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                fs.flush();
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }
}
