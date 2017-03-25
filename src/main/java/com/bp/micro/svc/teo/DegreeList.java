package com.bp.micro.svc.teo;

import java.util.List;

public class DegreeList extends SubRecords {
  private static final long serialVersionUID = 1L;
  private List<Degree> degrees;

  public List<Degree> getDegrees() {
    return degrees;
  }

  public void setDegrees(List<Degree> degrees) {
    this.degrees = degrees;
  }

}
