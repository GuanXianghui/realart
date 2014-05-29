package com.realart.utils;

import java.io.*;

/**
 * �ļ�������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-1 11:33
 */
public class FileUtil {
    /**
     * �����С
     */
    private static final int BUFFER_SIZE = 1444;

    /**
     * �����ļ�
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst) {
        try {
            int byteRead;
            if (src.exists()) { //�ļ�����ʱ
                InputStream inStream = new FileInputStream(src); //����ԭ�ļ�
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
            System.out.println("���Ƶ����ļ���������");
            e.printStackTrace();
        }
    }
}
