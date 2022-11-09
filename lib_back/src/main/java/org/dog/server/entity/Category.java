package org.dog.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/18 13:56
 * @Description:
 */

@Data
public class Category {

  private Integer id;

  private String name;

  private String remark;

  private Integer pid;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate createtime;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate updatetime;

  private List<Category> children;
}
