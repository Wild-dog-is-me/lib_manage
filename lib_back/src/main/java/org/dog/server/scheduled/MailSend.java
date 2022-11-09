package org.dog.server.scheduled;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.dog.server.utils.SendQQMailUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Odin
 * @Date 2022/11/7 17:26
 * @Description:
 */

@Component
public class MailSend {

    @Resource
    private SendQQMailUtil sender;

    @XxlJob(value = "sendMail")
    public ReturnT sendEmail() {
        sender.sendMail();
        return ReturnT.SUCCESS;
    }
}
