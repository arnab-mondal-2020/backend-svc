package com.bp.micro.svc.teo;

import java.io.Serializable;

public class SubRecords implements Serializable {
  private static final long serialVersionUID = 1L;
  private int fromReord;
  private int toRecord;
  private int totalRecord;
  private int recordSet;
  private int recordPerPage;
  private int pageNo;
  private String recordCountStr;
  private String pageNoStr;

  public int getFromReord() {
    return fromReord;
  }

  public void setFromReord(int fromReord) {
    this.fromReord = fromReord;
  }

  public int getToRecord() {
    return toRecord;
  }

  public void setToRecord(int toRecord) {
    this.toRecord = toRecord;
  }

  public int getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(int totalRecord) {
    this.totalRecord = totalRecord;
  }

  public int getRecordSet() {
    return recordSet;
  }

  public void setRecordSet(int recordSet) {
    this.recordSet = recordSet;
  }

  public int getRecordPerPage() {
    return recordPerPage;
  }

  public void setRecordPerPage(int recordPerPage) {
    this.recordPerPage = recordPerPage;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public String getRecordCountStr() {
    return recordCountStr;
  }

  public void setRecordCountStr(String recordCountStr) {
    this.recordCountStr = recordCountStr;
  }

  public String getPageNoStr() {
    return pageNoStr;
  }

  public void setPageNoStr(String pageNoStr) {
    this.pageNoStr = pageNoStr;
  }
}
