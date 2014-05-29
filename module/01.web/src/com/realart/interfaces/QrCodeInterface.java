package com.realart.interfaces;

/**
 * 二维码实体基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface QrCodeInterface extends BaseInterface {
    /**
     * 状态 1 未被使用 2 已被使用
     */
    public static final int STATE_NOT_USE = 1;
    public static final int STATE_USED = 2;

    /**
     * 未绑定艺术品初始id
     */
    public static final int QR_CODE_NOT_USE_ART_ID = 0;

    /**
     * 艺术品绑定二维码地址前缀
     */
    public static final String QR_CODE_URL_PREFIX = "http://www.jdzzyw.com/qr/";
}
