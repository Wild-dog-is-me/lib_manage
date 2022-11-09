package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/10 11:13
 * @Description:
 */

@Data
public class AdminPageRequest extends BaseRequest {

  public String username;

  public String phone;

  public String email;

}
