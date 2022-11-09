package org.dog.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author Odin
 * @Date 2022/10/10 08:32
 * @Description:读者
 */

@Data
public class User {

  private Integer id;

  private String name;

  private String username;

  private Integer age;

  private Integer score;

  private Integer account;

  private String sex;

  private String phone;

  private String email;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createtime;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date updatetime;

  private boolean status;
}
