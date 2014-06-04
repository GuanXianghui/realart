package com.realart.interfaces;

/**
 * 用户TOKEN实体基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface UserTokenInterface extends BaseInterface {
    /**
     * 状态 0 有效 1 失效
     */
    public static final int STATE_EFFECTIVE = 0;
    public static final int STATE_NOT_EFFECTIVE = 1;
}
