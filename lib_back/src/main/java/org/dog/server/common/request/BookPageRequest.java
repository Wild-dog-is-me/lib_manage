package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/21 10:12
 * @Description:
 */
@Data
public class BookPageRequest extends BaseRequest{
  private String name;
  private String bookNo;
}
