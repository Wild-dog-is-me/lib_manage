package org.dog.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.dog.server.entity.SendInfo;

/**
 * @Author Odin
 * @Date 2022/11/7 16:51
 * @Description:
 */

@Mapper
public interface SendInfoMapper extends BaseMapper<SendInfo> {

    @Update("truncate table send_info")
    void emptyTable();

}
