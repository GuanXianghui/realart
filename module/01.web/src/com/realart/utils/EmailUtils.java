package com.realart.utils;

import com.realart.interfaces.BaseInterface;
import com.realart.utils.javamail.MailSenderInfo;
import com.realart.utils.javamail.SimpleMailSender;
import org.apache.log4j.Logger;

/**
 * �ʼ�������
 * User: Gxx
 * Time: 2014-03-25 16:22
 */
public class EmailUtils implements BaseInterface
{
    /**
     * ��־������
     */
    public static Logger logger = Logger.getLogger(EmailUtils.class);

    /**
     * �����ʼ�
     * @param title
     * @param content
     * @param emails �ʼ����ŷָ�
     * @return
     * @throws Exception
     */
    public static boolean sendEmail(String title, String content, String emails) throws Exception
    {
        String emailHost = PropertyUtil.getInstance().getProperty(EMAIL_HOST);
        String emailPort = PropertyUtil.getInstance().getProperty(EMAIL_PORT);
        String emailName = PropertyUtil.getInstance().getProperty(EMAIL_NAME);
        String emailPassword = PropertyUtil.getInstance().getProperty(EMAIL_PASSWORD);
        logger.info("emailHost=[" + emailHost + "],emailPort=[" + emailPort + "],emailName=[" + emailName +
                "],emailPassword=[" + emailPassword + "],title=[" + title + "],content=[" + content +
                "],emails=[" + emails + "]");
        /**
         * �ʼ����ŷָ�
         * ����ʼ�����
         */
        for(int i=0;i<emails.split(",").length;i++){
            String[] toAddress = emails.split(",");
            //�����ʼ���Ҫʹ�õĻ�����Ϣ
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost(emailHost);//�����ʼ�����������
            mailInfo.setMailServerPort(emailPort);//�˿�
            mailInfo.setValidate(true);//У���û���
            mailInfo.setUserName(emailName);//�����û���
            mailInfo.setPassword(emailPassword);//��������
            mailInfo.setFromAddress(emailName);//�����û���
            mailInfo.setToAddress(toAddress[i]);//���շ�����
            mailInfo.setSubject(title);//�ʼ�̧ͷ
            mailInfo.setContent(content);//�ʼ�����
            //���ʼ��������������ʼ���������
            SimpleMailSender sms = new SimpleMailSender();
            //sms.sendTextMail(mailInfo);//���������ʽ
            sms.sendHtmlMail(mailInfo);//����html��ʽ
        }
        return true;
    }

    /**
     * main����
     * @param param
     */
    public static void main(String[] param) throws Exception {
        EmailUtils.sendEmail("����title", "<font color='green'>������������xxxxxxxxx</font>", "419066357@qq.com,419066357@163.com");
    }
}
