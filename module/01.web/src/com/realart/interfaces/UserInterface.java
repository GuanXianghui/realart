package com.realart.interfaces;

/**
 * 用户实体基础接口
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-29 13:16
 */
public interface UserInterface extends BaseInterface {
    /**
     * 用户类型 1 评论专员 2 艺术家
     */
    public static final int USER_TYPE_REVIEW = 1;
    public static final int USER_TYPE_ARTIST = 2;

    /**
     * 状态 0 待审核 1 审核通过正常 2 审核失败 3 锁定，评论会员直接审核通过，艺术家需要管理员审核
     */
    public static final int USER_STATE_NEED_CHECK = 0;
    public static final int USER_STATE_NORMAL = 1;
    public static final int USER_STATE_CHECK_FAILED = 2;
    public static final int USER_STATE_LOCK = 3;

    /**
     * 字段必输项 1 必输 2 非必输
     */
    public static final int COLUMN_NEED_YES = 1;
    public static final int COLUMN_NEED_NO = 2;

    /**
     * 字段类型 1 字符串 2 图片/文件
     */
    public static final int COLUMN_TYPE_STRING = 1;
    public static final int COLUMN_TYPE_FILE = 2;
}
