package org.dog.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.dog.server.entity.SendInfo;

/**
 * @Author Odin
 * @Date 2022/11/7 17:00
 * @Description:
 */
public interface SendInfoService extends IService<SendInfo> {
    void saveInfo();
}
