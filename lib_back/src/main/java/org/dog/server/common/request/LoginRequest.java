package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/14 13:37
 * @Description:
 */

@Data
public class LoginRequest {

  private String username;

  private String password;


}
