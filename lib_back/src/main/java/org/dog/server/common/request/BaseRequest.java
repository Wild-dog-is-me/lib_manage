package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/10 11:14
 * @Description:
 */

@Data
public class BaseRequest {
  private Integer pageNum = 1;
  private Integer pageSize = 10;
}
