package org.dog.server.common.request;

import lombok.Data;

/**
 * @Author Odin
 * @Date 2022/10/22 01:18
 * @Description:
 */

@Data
public class BorrowPageRequest extends BaseRequest{

  private String bookName;

  private String bookNo;

  private String userName;

}
