package com.realart.interfaces;

/**
 * �û�ʵ������ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface UserInterface extends BaseInterface {
    /**
     * �û����� 1 ����רԱ 2 ������
     */
    public static final int USER_TYPE_REVIEW = 1;
    public static final int USER_TYPE_ARTIST = 2;

    /**
     * ״̬ 0 ����� 1 ���ͨ������ 2 ���ʧ�� 3 ���������ۻ�Աֱ�����ͨ������������Ҫ����Ա���
     */
    public static final int USER_STATE_NEED_CHECK = 0;
    public static final int USER_STATE_NORMAL = 1;
    public static final int USER_STATE_CHECK_FAILED = 2;
    public static final int USER_STATE_LOCK = 3;

    /**
     * �ֶα����� 1 ���� 2 �Ǳ���
     */
    public static final int COLUMN_NEED_YES = 1;
    public static final int COLUMN_NEED_NO = 2;

    /**
     * �ֶ����� 1 �ַ��� 2 ͼƬ/�ļ�
     */
    public static final int COLUMN_TYPE_STRING = 1;
    public static final int COLUMN_TYPE_FILE = 2;
}
