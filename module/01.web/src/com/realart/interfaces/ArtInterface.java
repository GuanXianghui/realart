package com.realart.interfaces;

/**
 * 艺术品实体基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface ArtInterface extends BaseInterface {
    /**
     * 状态 0 待审核 1 审核通过 2 审核失败
     */
    public static final int NEED_CHECK = 0;
    public static final int NORMAL = 1;
    public static final int CHECK_FAILED = 2;

    /**
     * 默认位置位图 默认0000000000
     */
    public static final String DEFAULT_LOCATION_BIT = "0000000000";
}
