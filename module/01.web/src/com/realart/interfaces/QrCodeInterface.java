package com.realart.interfaces;

/**
 * ��ά��ʵ������ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface QrCodeInterface extends BaseInterface {
    /**
     * ״̬ 1 δ��ʹ�� 2 �ѱ�ʹ��
     */
    public static final int STATE_NOT_USE = 1;
    public static final int STATE_USED = 2;

    /**
     * δ������Ʒ��ʼid
     */
    public static final int QR_CODE_NOT_USE_ART_ID = 0;

    /**
     * ����Ʒ�󶨶�ά���ַǰ׺
     */
    public static final String QR_CODE_URL_PREFIX = "http://www.jdzzyw.com/qr/";
}
