package com.realart;

import com.realart.interfaces.ParamInterface;
import com.realart.interfaces.SymbolInterface;
import com.realart.interfaces.UserInterface;
import com.realart.utils.ParamUtil;
import org.apache.commons.lang.StringUtils;

/**
 * ɾ�����ʧ��ԭ��
 *
 * @author Gxx
 * @module oa
 * @datetime 14-5-10 19:20
 */
public class DeleteCWRAction extends BaseAction implements UserInterface{
    private String cwr;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("cwr:[" + cwr + "]");
        //���ʧ��ԭ��
        String checkWrongReason = ParamUtil.getInstance().getValueByName(ParamInterface.CHECK_WRONG_REASON);
        //�µ� ���ʧ��ԭ��
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
        ParamUtil.getInstance().updateParam(ParamInterface.CHECK_WRONG_REASON, newCheckWrongReason, "���ʧ��ԭ��");

        //ˢ�»���
        ParamUtil.refresh();

        message = "ɾ�����ʧ��ԭ��ɹ���";
        return SUCCESS;
    }

    public String getCwr() {
        return cwr;
    }

    public void setCwr(String cwr) {
        this.cwr = cwr;
    }
}
