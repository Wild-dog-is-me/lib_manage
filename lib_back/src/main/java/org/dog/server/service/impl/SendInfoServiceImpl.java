package org.dog.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.dog.server.entity.Borrow;
import org.dog.server.entity.SendInfo;
import org.dog.server.entity.User;
import org.dog.server.mapper.BorrowMapper;
import org.dog.server.mapper.SendInfoMapper;
import org.dog.server.mapper.UserMapper;
import org.dog.server.service.SendInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/11/7 17:01
 * @Description:
 */

@Service
public class SendInfoServiceImpl extends ServiceImpl<SendInfoMapper, SendInfo> implements SendInfoService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BorrowMapper borrowMapper;

    @Resource
    private SendInfoMapper sendInfoMapper;

    public User getUnReturnUser(Borrow borrow) {
        LocalDate returnDate = borrow.getReturnDate();
        LocalDate createDate = borrow.getCreatetime();
        int days = Integer.valueOf((int) (returnDate.toEpochDay() - createDate.toEpochDay()));
        if (days <= 1) {
            User user = this.userMapper.getByUsername(borrow.getUserNo());
            return user;
        } else {
            return null;
        }
    }

    @Override
    @XxlJob(value = "saveInfo")
    public void saveInfo() {
        List<Borrow> borrowList = borrowMapper.list();
        sendInfoMapper.emptyTable();
        for (Borrow borrow : borrowList) {
            User user = getUnReturnUser(borrow);
            if (user == null) {
                continue;
            } else {
                SendInfo sendInfo = new SendInfo();
                sendInfo.setReceiver(user.getName());
                sendInfo.setBookName(borrow.getBookName());
                sendInfo.setReceiverEmail(user.getEmail());
                sendInfo.setReturnDate(borrow.getReturnDate());
                sendInfoMapper.insert(sendInfo);
            }
        }
    }
}
