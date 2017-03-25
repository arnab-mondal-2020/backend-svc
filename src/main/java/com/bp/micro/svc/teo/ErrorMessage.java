package com.bp.micro.svc.teo;

import java.io.Serializable;


public class ErrorMessage implements Serializable {
  private static final long serialVersionUID = 1L;
  private String exceptionId;
  private String exceptionMessage;
  private String type;
  private String time;

  public String getExceptionId() {
    return exceptionId;
  }

  public void setExceptionId(String exceptionId) {
    this.exceptionId = exceptionId;
  }

  public String getExceptionMessage() {
    return exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage) {
    this.exceptionMessage = exceptionMessage;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

}
