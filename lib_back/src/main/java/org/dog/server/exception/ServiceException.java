package org.dog.server.exception;

/**
 * @Author Odin
 * @Date 2022/10/10 15:19
 * @Description:
 */

public class ServiceException extends RuntimeException{

  private String code;

  public String getCode() {
    return code;
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String code, String message) {
    super(message);
    this.code = code;
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}

