package com.bp.micro.svc.common;

public final class BackendUtil {
  private BackendUtil() {}

  public static int populateRecordSet(int count, int noOfRecordPerPage) {
    int recordSet = 1;
    if (count % noOfRecordPerPage == 0) {
      recordSet = count / noOfRecordPerPage;
    } else {
      recordSet = count / noOfRecordPerPage + 1;
    }
    return recordSet;
  }
}
