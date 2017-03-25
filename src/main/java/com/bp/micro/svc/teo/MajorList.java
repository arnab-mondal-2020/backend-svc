package com.bp.micro.svc.teo;

import java.util.List;

public class MajorList extends SubRecords {

  private static final long serialVersionUID = 1L;
  private List<Major> majors;

  public List<Major> getMajors() {
    return majors;
  }

  public void setMajors(List<Major> majors) {
    this.majors = majors;
  }
}
