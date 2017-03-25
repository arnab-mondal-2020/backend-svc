package com.bp.micro.svc.teo;


public class AdminUser extends BPBase {
  private static final long serialVersionUID = 1L;

  private String userNumber;
  private String fullName;
  private String address;
  private String pin;
  private String contactNo;
  private String email;
  private String dpPath;
  private String highestDegree;
  private String occupation;
  private String authLevel;
  private String sex;


  public String getUserNumber() {
    return userNumber;
  }

  public void setUserNumber(String userNumber) {
    this.userNumber = userNumber;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDpPath() {
    return dpPath;
  }

  public void setDpPath(String dpPath) {
    this.dpPath = dpPath;
  }

  public String getHighestDegree() {
    return highestDegree;
  }

  public void setHighestDegree(String highestDegree) {
    this.highestDegree = highestDegree;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getAuthLevel() {
    return authLevel;
  }

  public void setAuthLevel(String authLevel) {
    this.authLevel = authLevel;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public String toString() {
    return "AdminUser [userNumber=" + userNumber + ", fullName=" + fullName + ", address=" + address
        + ", pin=" + pin + ", contactNo=" + contactNo + ", email=" + email + ", dpPath=" + dpPath
        + ", highestDegree=" + highestDegree + ", occupation=" + occupation + ", authLevel="
        + authLevel + ", sex=" + sex + "]";
  }



}
