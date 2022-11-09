package org.dog.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author Odin
 * @Date 2022/10/18 13:58
 * @Description:
 */

@Data
public class BaseEntity {

  private static final long serialVersionUID = 1L;

  private Integer id;

  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate createtime;

  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate updatetime;

}
