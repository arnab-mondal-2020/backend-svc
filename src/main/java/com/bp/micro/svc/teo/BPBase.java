package com.bp.micro.svc.teo;

import java.io.Serializable;

public class BPBase implements Serializable {
  private static final long serialVersionUID = 1L;
  private String updateUserNumber;
  private String updateTimeStamp;


  public String getUpdateTimeStamp() {
    return updateTimeStamp;
  }

  public void setUpdateTimeStamp(String updateTimeStamp) {
    this.updateTimeStamp = updateTimeStamp;
  }

  public String getUpdateUserNumber() {
    return updateUserNumber;
  }

  public void setUpdateUserNumber(String updateUserNumber) {
    this.updateUserNumber = updateUserNumber;
  }


}
