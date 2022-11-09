package org.dog.server.exception;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.dog.server.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Odin
 * @Date 2022/10/10 15:18
 * @Description:
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler(value = ServiceException.class)
  public Result serviceExceptionError(ServiceException e) {
    log.error("业务异常",e);
    log.error("业务异常代码{}",e.getCode(), e.getCode());
    String code = e.getCode();
    if (StrUtil.isNotBlank(code)) {
      return Result.error(code,e.getMessage());
    }
    return Result.error(e.getMessage());
  }

  @ExceptionHandler(value = Exception.class)
  public Result exceptionError(Exception e) {
    log.error("系统错误", e);
    return Result.error("系统错误");
  }

}
