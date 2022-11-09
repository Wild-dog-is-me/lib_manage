package org.dog.server.utils;

import org.dog.server.entity.SendInfo;
import org.dog.server.mapper.SendInfoMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/11/7 17:23
 * @Description:
 */
@Component
public class SendQQMailUtil {

    private static final String SENDER = "1427774041@qq.com";

    private static final String TITLE = "江苏师范大学科文学院图书馆";

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private SendInfoMapper sendInfoMapper;

    public void sendEmail(SendInfo sendInfo) {
        //建立邮件消息
        SimpleMailMessage mail = new SimpleMailMessage();

        //发送者
        mail.setFrom(SENDER);
        //接收者
        mail.setTo(sendInfo.getReceiverEmail());
        //发送的标题
        mail.setSubject(TITLE);
        String message = sendInfo.getReceiver() +
                "同学，你好。近期您在本馆借阅的图书《" +
                sendInfo.getBookName() +
                "》归还日期为" +
                sendInfo.getReturnDate() +
                ",请按时归还，感谢配合";
        //发送的内容
        mail.setText(message);
        javaMailSender.send(mail);
    }

    public void sendMail() {
        List<SendInfo> sendInfos = sendInfoMapper.selectList(null);
        for (SendInfo sendInfo : sendInfos) {
            sendEmail(sendInfo);
        }
    }
}
