package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;

/**
 * 删除审核失败原因
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteCWRAction extends BaseAction implements UserInterface{
    private String cwr;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        logger.info("cwr:[" + cwr + "]");
        //审核失败原因
        String checkWrongReason = ParamUtil.getInstance().getValueByName(ParamInterface.CHECK_WRONG_REASON);
        //新的 审核失败原因
        String newCheckWrongReason = StringUtils.EMPTY;
        String[] checkWrongReasonArray = checkWrongReason.split("\\|");
        for(int i=0;i<checkWrongReasonArray.length;i++)
        {
            if(i+1 == Integer.parseInt(cwr)){
                continue;
            }
            if(StringUtils.isNotBlank(newCheckWrongReason)){
                newCheckWrongReason += SymbolInterface.SYMBOL_STAND;
            }
            newCheckWrongReason += checkWrongReasonArray[i];
        }
        logger.info("newCheckWrongReason=>" + newCheckWrongReason);
        ParamUtil.getInstance().updateParam(ParamInterface.CHECK_WRONG_REASON, newCheckWrongReason, "审核失败原因");

        //刷新缓存
        ParamUtil.refresh();

        message = "删除审核失败原因成功！";
        return SUCCESS;
    }

    public String getCwr() {
        return cwr;
    }

    public void setCwr(String cwr) {
        this.cwr = cwr;
    }
}
