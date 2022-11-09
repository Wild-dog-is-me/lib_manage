package org.dog.server.common.dto;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/14 13:56
 * @Description:
 */

@Data
public class LoginDTO {

  private Integer id;

  private String username;

  private String phone;

  private String email;

  private String token;

}
