package com.realart.interfaces;

/**
 * ����Ʒʵ������ӿ�
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface ArtInterface extends BaseInterface {
    /**
     * ״̬ 0 ����� 1 ���ͨ�� 2 ���ʧ��
     */
    public static final int NEED_CHECK = 0;
    public static final int NORMAL = 1;
    public static final int CHECK_FAILED = 2;

    /**
     * Ĭ��λ��λͼ Ĭ��0000000000
     */
    public static final String DEFAULT_LOCATION_BIT = "0000000000";
}
