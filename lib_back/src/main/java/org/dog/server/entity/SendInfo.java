package org.dog.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author Odin
 * @Date 2022/11/7 16:50
 * @Description:
 */
@Data
public class SendInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * 邮件接收人姓名
     */
    private String receiver;

    /**
     * 未归还书名
     */
    private String bookName;

    /**
     * 邮件接收人邮箱
     */
    private String receiverEmail;

    /**
     * 预计还书日期
     */
    private LocalDate returnDate;

}
