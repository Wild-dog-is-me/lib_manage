package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/16 02:54
 * @Description:
 */

@Data
public class PasswordRequest {

  private String username;

  private String password;

  private String newPass;

}
